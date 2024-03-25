def call(boolean abortOnFailure = false, boolean abortPipeline = false) {
    // Verificar si se debe abortar siempre según el parámetro
    if (abortPipeline) {
        error "Pipeline aborted by user request."
    }
    
    // Leer el nombre de la rama de Git de la variable de entorno
    def gitBranch = env.BRANCH_NAME
    
    // Determinar si se debe cortar el pipeline según la heurística
    if (abortOnFailure || gitBranch == 'master' || gitBranch.startsWith('hotfix')) {
        error "Pipeline aborted due to Quality Gate failure or branch name: ${gitBranch}."
    } else {
        // Ejecutar el escaneo de SonarQube (o un mensaje de prueba)
        sh 'echo "Ejecución de las pruebas de calidad de código"'
        
        // Esperar 5 minutos con un timeout para el resultado
        def qualityGateStatus = waitForQualityGate()
        
        // Evaluar el resultado del QualityGate y abortar el pipeline si es necesario
        if (qualityGateStatus != 'OK') {
            error "Quality Gate failure detected. Aborting pipeline."
        }
    }
}

def waitForQualityGate() {
    // Esperar 5 minutos con un timeout
    def qualityGateStatus = timeout(time: 5, unit: 'SECONDS') {
        // Simular la espera de verificación del Quality Gate
        // En una implementación real, aquí se llamaría a SonarQube API para verificar el estado del Quality Gate
        
        // Suponiendo que el estado del Quality Gate es OK
        return 'OK'
    }
    
    return qualityGateStatus
}