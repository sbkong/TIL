# Java - jacoco
### 설치 방법
- build.gradle 파일에 아래 내용 추가
```build.gradle

plugins {  
    id 'jacoco'  
}

jacoco {  
    toolVersion = "0.8.8"  
}  
  
test {  
    useJUnitPlatform()  
    finalizedBy jacocoTestReport  
    finalizedBy jacocoTestCoverageVerification  
}  
  
jacocoTestCoverageVerification {  
    violationRules {  
        rule {  
            limit {  
                minimum = 0.70  
            }  
        }    }}  
  
jacocoTestReport {  
    reports {  
        xml.required = true  
        csv.required = false  
        html.outputLocation = layout.buildDirectory.dir('jacocoHtml')  
    }  
}
```
### 사용 방법
#### cli
```cli
./gradlew clean test jacocoTestReport
```

#### Jetbrains IDE
> 테스트 리포트 생성
- Gradle > Tasks > verification > jacocoTestReport

> 테스트 커버리지 목표 도달 확인
- Gradle > Tasks > verification > jacocoTestCoverageVerification

![[Pasted image 20240729155017.png]]

#### 결과 확인
- jacoco task를 실행하면 아래 경로에 결과 파일 생성됨

- Test Summary
```
$PROJECT_HOME$\build\reports\tests\test\index.html
```
![[Pasted image 20240729155702.png]]

- Total Test Coverage
```
$PROJECT_HOME$\build\jacocoHtml\index.html
```
![[Pasted image 20240729155652.png]]

# vue.js - Istanbul, Jest
### 설치
- 프로젝트 HOME 경로에서 cli로 패키지 설치
```cli
npm install --save-dev jest @vue/test-utils babel-jest jest-serializer-vue vue-jest @istanbuljs/nyc-config-babel babel-plugin-istanbul
```

- package.json 파일 설정
```package.json
{
  "scripts": {  
	...
	
	"test:unit": "jest"  
  },
  ...

  "jest": {
    "moduleFileExtensions": [
      "js",
      "json",
      "vue"
    ],
    "transform": {
      "^.+\\.vue$": "vue-jest",
      "^.+\\.js$": "babel-jest"
    },
    "snapshotSerializers": [
      "jest-serializer-vue"
    ],
    "collectCoverage": true,
    "collectCoverageFrom": [
      "src/**/*.{js,vue}",
      "!**/node_modules/**"
    ],
    "coverageReporters": [
      "html",
      "text-summary"
    ]
  }
  
  ...
}
```

- 프로젝트 홈에 .babelrc 파일 생성 및 설정
```.babelrc
{
  "presets": [
    "@babel/preset-env"
  ],
  "plugins": [
    "babel-plugin-istanbul"
  ]
}
```

- 프로젝트 홈 경로에서 아래 경로 및 테스트 파일 생성
```
$PROJECT_HOME$\tests\unit\[테스트 할 파일]
```

- 테스트 실행
### jest 실행
```
npm run test:unit
```

### 결과 확인
- 아래 경로에서 파일 확인
```
$PROJECT_HOME$\coverage\index.html
```
![[Pasted image 20240729163436.png]]

# android
### 패키지 설치
- build.gradle (project level)
```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
	    ...
        classpath 'org.jacoco:org.jacoco.core:0.8.7'
    }
}

```


#### android
```
apply plugin: 'jacoco'

android {
    compileSdkVersion 28
    defaultConfig {
        ...
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    ...
    
    testOptions {
        unitTests.all {
            jacoco {
                includeNoLocationClasses = true
            }
        }
    }
}

// deprecated
//jacoco {
//    toolVersion = "0.8.7"
//}

task jacocoTestReport(type: JacocoReport, dependsOn: ['testDebugUnitTest']) {
    reports {
        xml.required = true
        csv.required = false
        html.required = true
        html.outputLocation = file("$buildDir/reports/jacoco")
    }

    def fileFilter = ['**/R.class', '**/R$*.class', '**/BuildConfig.*', '**/Manifest*.*', '**/*Test*.*']
    def debugTree = fileTree(dir: "$buildDir/intermediates/javac/debug", excludes: fileFilter)
    def mainSrc = "$projectDir/src/main/java"

    sourceDirectories.setFrom(files([mainSrc]))
    classDirectories.setFrom(files([debugTree]))
    executionData.setFrom(fileTree(dir: "$buildDir", includes: ['jacoco/testDebugUnitTest.exec']))

    doFirst {
        new File("$buildDir/intermediates/javac/debug").eachFileRecurse { file ->
            if (!file.name.endsWith('.class')) {
                file.delete()
            }
        }
    }
}

```
#### androidx
- build.gradle (app level)
```
apply plugin: 'jacoco'

android {
    compileSdkVersion 30
    defaultConfig {
        applicationId "com.example.myapp"
        minSdkVersion 21
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    testOptions {
        unitTests.all {
            jacoco {
                includeNoLocationClasses = true
            }
        }
    }
}

jacoco {
    toolVersion = "0.8.7"
}

task jacocoTestReport(type: JacocoReport, dependsOn: ['testDebugUnitTest']) {
    reports {
        xml.required = true
        csv.required = false
        html.required = true
        html.outputLocation = file("$buildDir/reports/jacoco")
    }

    def fileFilter = ['**/R.class', '**/R$*.class', '**/BuildConfig.*', '**/Manifest*.*', '**/*Test*.*']
    def debugTree = fileTree(dir: "$buildDir/intermediates/javac/debug", excludes: fileFilter)
    def mainSrc = "$projectDir/src/main/java"

    sourceDirectories.setFrom(files([mainSrc]))
    classDirectories.setFrom(files([debugTree]))
    executionData.setFrom(fileTree(dir: "$buildDir", includes: ['jacoco/testDebugUnitTest.exec']))

    doFirst {
        new File("$buildDir/intermediates/javac/debug").eachFileRecurse { file ->
            if (!file.name.endsWith('.class')) {
                file.delete()
            }
        }
    }
}

```

### jest 실행
```
./gradlew clean testDebugUnitTest jacocoTestReport
```

### 결과 확인
- 아래 경로에서 파일 확인
```
$PROJECT_HOME$/build/reports/jacoco/html/index.html
```

# coverage 높이는 방법

1. main 패키지의 파일 경로와 동일한 하위 경로에 테스트 파일 생성


![[Pasted image 20240729155840.png]] main/java/kr.triniti.dl.binder/service/FileService.java

![[Pasted image 20240729155851.png]] test/java/kr.triniti.dl.binder/service/FileServiceTest.java

2. 테스트 수행
   ![[Pasted image 20240729160144.png]]

3. 이후 jacoco/jest 수행
