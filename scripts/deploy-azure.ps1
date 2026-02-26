<#
PowerShell script para desplegar la aplicación Spring Boot en Azure
Uso: ejecutar en PowerShell (Administrativo si es necesario)
Requisitos previos:
 - Azure CLI instalado (az)
 - Docker instalado
 - Maven (o use ./mvnw para Windows: mvnw.cmd)
 - Estar en la raíz del proyecto (donde está Dockerfile)

El script: crea Resource Group, ACR, construye imagen Docker, la sube a ACR,
crea App Service Plan y Web App for Containers y configura variables.
#>

param(
    [string]$RG = "mssoporte2-rg",
    [string]$LOCATION = "eastus",
    [string]$ACR = "mssoporte2acr",
    [string]$APP = "mssoporte2-app",
    [string]$PLAN = "mssoporte2-plan",
    [string]$IMAGE_NAME = "microservicio-soporte2",
    [string]$IMAGE_TAG = "latest",
    [int]$WEBSITES_PORT = 8080
)

function Check-Command {
    param([string]$cmd)
    return (Get-Command $cmd -ErrorAction SilentlyContinue) -ne $null
}

if (-not (Check-Command -cmd "az")) {
    Write-Error "Azure CLI 'az' no encontrado. Instala Azure CLI e intenta de nuevo."; exit 1
}
if (-not (Check-Command -cmd "docker")) {
    Write-Error "Docker no encontrado. Instala Docker e intenta de nuevo."; exit 1
}

Write-Host "Comprobando sesión de Azure..."
try {
    $account = az account show --only-show-errors | Out-String
} catch {
    Write-Host "No hay sesión activa. Abriendo navegador para iniciar sesión..."
    az login
}

Write-Host "Creando/asegurando Resource Group: $RG ($LOCATION)"
az group create --name $RG --location $LOCATION

Write-Host "Creando/asegurando Azure Container Registry: $ACR"
az acr create --resource-group $RG --name $ACR --sku Basic --admin-enabled true --only-show-errors

Write-Host "Compilando artefacto con Maven (skip tests)"
if (Test-Path "mvnw.cmd") {
    & .\mvnw.cmd clean package -DskipTests
} elseif (Test-Path "mvnw") {
    & ./mvnw clean package -DskipTests
} else {
    mvn clean package -DskipTests
}

$imageFull = "$($ACR).azurecr.io/$IMAGE_NAME:$IMAGE_TAG"

Write-Host "Construyendo imagen Docker: $imageFull"
docker build -t $imageFull .

Write-Host "Iniciando sesión en ACR $ACR"
az acr login --name $ACR

Write-Host "Pushing imagen a ACR"
docker push $imageFull

Write-Host "Creando App Service Plan (Linux): $PLAN"
az appservice plan create --name $PLAN --resource-group $RG --is-linux --sku B1 --only-show-errors

Write-Host "Creando Web App for Containers: $APP"
az webapp create --resource-group $RG --plan $PLAN --name $APP --deployment-container-image-name $imageFull --only-show-errors

Write-Host "Obteniendo credenciales ACR y configurando acceso al registro desde Web App"
$acrCreds = az acr credential show --name $ACR --resource-group $RG --query "{user:username,pass:passwords[0].value}" -o tsv
$acrUser, $acrPass = $acrCreds -split "\t"

az webapp config container set --name $APP --resource-group $RG --docker-custom-image-name $imageFull --docker-registry-server-url https://$($ACR).azurecr.io --docker-registry-server-user $acrUser --docker-registry-server-password $acrPass --only-show-errors

Write-Host "Configurando variables de aplicación: WEBSITES_PORT=$WEBSITES_PORT"
az webapp config appsettings set --resource-group $RG --name $APP --settings WEBSITES_PORT=$WEBSITES_PORT --only-show-errors

Write-Host "Despliegue finalizado. Obteniendo URL de la aplicación..."
$appUrl = az webapp show --name $APP --resource-group $RG --query defaultHostName -o tsv

Write-Host "Aplicación desplegada en: https://$appUrl"
Write-Host "Para ver logs en tiempo real:
 az webapp log tail --name $APP --resource-group $RG"

Write-Host "Si necesitas configurar variables adicionales (BD, secretos), usa 'az webapp config appsettings set' o actualiza en el Portal de Azure."
