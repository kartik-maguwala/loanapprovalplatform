#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "chmod +x mvnw"
        sh "./mvnw -ntp clean"
    }

//    stage('backend tests') {
//        try {
//            sh "./mvnw -ntp verify"
//        } catch(err) {
//            throw err
//        } finally {
//            junit '**/target/test-results/**/TEST-*.xml'
//        }
//    }

    stage('packaging') {
        sh "./mvnw -ntp package -DskipTests"
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }

    stage('quality analysis') {
        withSonarQubeEnv('localhost:9001') {
            sh "./mvnw -ntp initialize sonar:sonar"
        }
    }

    def dockerImage
    stage('publish docker') {
        sh "./mvnw -ntp jib:build"
    }
}
