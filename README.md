# WF-refactoring
SpringBoot ,JPA를 사용한 리팩토링 프로젝트
# WorkFlow 
WorkFlow(전자 결재) 시스템 개발

# 사용 기술
- Back-end : Java, Spring Boot, JPA, Spring MVC,Gradle
- DB : MySQL
- Developer Tools : intelliJ / MySQL Workbench 
- PostMan , SourceTree

# 브랜치 관리 :git-flow
```sh
master : 배포용 브랜치
develop : 기능 merge용 브랜치
feature : 기능 개발 브랜치
release : QA 브랜치
hotfix : master 브랜치로 배포 후 수정 브랜치
```

# RESTful API 
📃 [API 명세서](https://github.com/CJIHEE/WF-refactoring/wiki/%F0%9F%93%83-API-%EB%AA%85%EC%84%B8%EC%84%9C)


# 멀티 모듈 설계
![multi-module](https://github.com/CJIHEE/WF-refactoring/assets/110098108/a2968ebd-adaa-4d8b-b0e7-29419f7ff461)
```sh
api-module : Controller, Exception
domain-module :Service, DTO(Request, Response) Exception
data- module : Entity, Repository, Enum
```
설계이야기 https://gogowlgml.tistory.com/88
