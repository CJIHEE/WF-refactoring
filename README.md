현재 개발 진행중인 브랜치 - feature_document
# WF-refactoring
SpringBoot ,JPA를 사용한 리팩토링 프로젝트
# WorkFlow 
WorkFlow(전자 결재) 시스템 개발

# 사용 기술
- Back-end : Java, Spring Boot, JPA, Spring MVC,Gradle,Junit5, Spring Security, JWT, QueryDSL, JPQL
- DB : MySQL, Redis
- Developer Tools : intelliJ / MySQL Workbench 
- PostMan , SourceTree, Swagger

# 브랜치 관리 :git-flow
```sh
master : 배포용 브랜치
develop : 기능 merge용 브랜치
feature : 기능 개발 브랜치
release : QA 브랜치
hotfix : master 브랜치로 배포 후 수정 브랜치
```

# Docker를 활용한 배포 환경 구성
<img width="894" alt="image" src="https://github.com/CJIHEE/WF-refactoring/assets/110098108/0bfda94a-8c7f-41fc-8a87-8e7a748bd845">

# 객체 참조로 인한 결합도 상승을 줄이기 위해 약한 결합 맺기

- **객체 참조**
    
    - 연관 관계 설정 기준 : 함께 생성되고 함께 삭제되는 객체들<br>
    ex) document - attachment (attachment 객체의 생애 주기가 document에 종속적, 양방향 관계)
    
- **id참조**
    
    - 기준 외 객체들은 객체 간 연관 관계 없이 id 참조로 약한 결합<br>
    ex) employee - dept : id를 이용해 접근(dept는 약한 결합도 탐색인 repository를 통한 탐색)
    
    →이를 통해 패키지간 의존도 사이클이 없어졌다

# 멀티 모듈 설계

<img width="600" alt="multi-module 수정" src="https://github.com/CJIHEE/WF-refactoring/assets/110098108/5c483222-8ff8-49c1-b2d1-ccc69b1a1f33">

```sh
api-module : Controller, Exception , DTO(Request)
domain-module :Service, DTO(Request, Response) Exception
data- module : Entity, Repository
common-moudule : Enums
jwt-module : Service,DTO,Config,Security,Exception
Redis-module : config
```

설계이야기 https://gogowlgml.tistory.com/88

# Swaager를 통한 RESTful API 
📃 http://43.201.67.76:9000/swagger-ui.html

