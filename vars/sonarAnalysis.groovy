def call(boolean abortOnFailure = false, boolean abortPipeline = false) {
    // Ejecutar el escaneo de SonarQube (o un mensaje de prueba)
    sh 'echo "Ejecución de las pruebas de calidad de código"'
    
    // Esperar 5 minutos con un timeout para el resultado
    def qualityGateStatus = waitForQualityGate()
    
    // Evaluar el resultado del QualityGate y abortar el pipeline si es necesario
    if (abortOnFailure && qualityGateStatus != 'OK') {
        error "Quality Gate failure detected. Aborting pipeline."
    }
    
    // Abortar el pipeline si se indica
    if (abortPipeline) {
        error "Pipeline aborted by user request."
    }
}

def waitForQualityGate() {
    // Simular la espera de 5 minutos
    // En una implementación real, aquí se llamaría a SonarQube API para verificar el estado del Quality Gate
    
    // Suponiendo que el estado del Quality Gate es OK
    return 'OK'
}