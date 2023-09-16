package com.workFlow.WFrefactoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.workFlow.WFrefactoring")
@EnableJpaRepositories(basePackages = "com.workFlow.WFrefactoring.repository")
//어플리케이션 모듈과 queryDslConfig 클래스 모듈이 달라서 오류발생, 빈 주입이 안되서 reposiroty경로를 직접 설정
public class WfRefactoringApplication {

	public static void main(String[] args) {
 		SpringApplication.run(WfRefactoringApplication.class, args);
	}

}
