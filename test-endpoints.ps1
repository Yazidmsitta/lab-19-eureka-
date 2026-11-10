Write-Host "=================================================" -ForegroundColor Cyan
Write-Host " Verification des Endpoints Microservices Spring " -ForegroundColor Cyan
Write-Host "=================================================" -ForegroundColor Cyan

$endpoints = @(
    @{ Name = "Eureka Dashboard"; Url = "http://localhost:8761" },
    @{ Name = "Service Client (Direct)"; Url = "http://localhost:8088/clients" },
    @{ Name = "Service Voiture (Direct - Feign)"; Url = "http://localhost:8089/voitures" },
    @{ Name = "Gateway (Accueil)"; Url = "http://localhost:8888/" },
    @{ Name = "Gateway -> Clients (lb://)"; Url = "http://localhost:8888/clients" },
    @{ Name = "Gateway -> Voitures (lb://)"; Url = "http://localhost:8888/voitures" }
)

foreach ($ep in $endpoints) {
    try {
        $response = Invoke-WebRequest -Uri $ep.Url -Method Get -TimeoutSec 3 -UseBasicParsing
        if ($response.StatusCode -eq 200) {
            Write-Host "[OK 200] " -ForegroundColor Green -NoNewline
            Write-Host "$($ep.Name) : $($ep.Url)"
        } else {
            Write-Host "[WARN $($response.StatusCode)] " -ForegroundColor Yellow -NoNewline
            Write-Host "$($ep.Name) : $($ep.Url)"
        }
    } catch {
        Write-Host "[FAIL]   " -ForegroundColor Red -NoNewline
        Write-Host "$($ep.Name) : $($ep.Url) ($($_.Exception.Message))"
    }
}
Write-Host "=================================================" -ForegroundColor Cyan

