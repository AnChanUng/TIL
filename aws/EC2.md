### EC2 (Amazon Elastic Compute Cloud)
AWS에서 제공하는 클라우드 컴퓨팅
> 컴퓨터를 빌려서 원격으로 접속해 사용하는 서비스

### 프론트엔드는 EC2를 사용할까?
프론트엔드 웹 페이지를 배포할 때 AWS EC2를 사용할 수도 있지만, AWS EC2보다 vercel, netlify 또는 AWS S3를 사용해서 주로 배포

### Region
인프라를 지리적으로 나누어 배포한 각각의 데이터 센터

### Region 선택 기준
사람들이 애플리케이션을 사용할 때는 네트워크를 통해 통신하게 된다. 이 때, 사용자의 위치와 애플리케이션을 실행시키고 있는 컴퓨터와 위치가 멀면 멀수록 속도가 느려진다. 따라서 애플리케이션의 주된 사용자들의 위치와 지리적으로 가까운 리전(Region)을 선택하는 것이 유리

### EC2 셋팅 - 기본 설정
1. EC2의 이름을 설정하는 곳
<img width="400" height="214" alt="image" src="https://github.com/user-attachments/assets/5d63b930-4e81-4b32-a823-07392fd49a28" />

2. Application and OS Images (Amazon Machine Image)
<img width="300" height="214" alt="image" src="https://github.com/user-attachments/assets/1163cea2-62e4-40f4-a9a6-f643a0946d27" />

3. 인스턴스 유형
- 인스턴스 : AWS EC2에서 빌리는 컴퓨터 1대
- 인스턴스 유형 : 컴퓨터 사양

4. 키 페어(로그인)
<img width="511" height="552" alt="image" src="https://github.com/user-attachments/assets/92c1551e-3e04-4ab0-9946-fbe41fdab239" />
- 키 페어 이름은 어떤 EC2에 접근하기 위한 키 페어였는 지 알아볼 수 있게 지정하면 좋음
- `RSA`와 `.pem`을 선택한 후에 키 페어를 생성하면 된다. 
- 키 페어를 생성하면 파일이 하나 다운받아질텐데, 그 파일은 잃어버리면 안 되니 잘 보관해놔야 한다.

### EC2 셋팅하기 - 보안그룹 설정
네트워크 설정
<img width="681" height="580" alt="image" src="https://github.com/user-attachments/assets/87e17e84-832c-4fbf-b701-e9dfe7640f4e" />
<img width="689" height="760" alt="image" src="https://github.com/user-attachments/assets/7e610676-bda9-4ca6-9bd9-a9b1c96f6456" />

- 보안 그룹 : AWS 클라우드에서의 네트워크 보안

보안그룹 설정
<img width="696" height="466" alt="image" src="https://github.com/user-attachments/assets/93f7df35-9ac1-4ef2-ad72-182c638ee4d4" />
- 외부에서 EC2로 접근할 포트 : 22 (우리가 EC2에 원격 접속할 때 사용하는 포트)
- 80번 포트 : 백엔드 서버
- 소스 유형 위치 무관 : 어떤 IP에서든 전부 접근할 수 있게 만들기 위해
