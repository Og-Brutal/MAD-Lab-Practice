# PowerShell helper to create local apikey.properties file
param(
    [string]$Key
)
if (-not $Key) {
    Write-Host "Usage: .\create_apikey.ps1 -Key <YOUR_API_KEY>"
    exit 1
}
$path = "API_APP/app/src/main/assets/apikey.properties"
if (-not (Test-Path (Split-Path $path))) {
    New-Item -ItemType Directory -Path (Split-Path $path) -Force | Out-Null
}
"API_KEY=$Key" | Out-File -FilePath $path -Encoding UTF8
Write-Host "Created $path (this file is ignored by git)."