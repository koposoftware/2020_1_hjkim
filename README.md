# 프로젝트 제목

[프로젝트 홈페이지 - https://koposoftware.github.io/template/](https://koposoftware.github.io/template/)

# 1. 프로젝트 개요

이것은 프로젝트 개요입니다. 글과 그림을 이용하여 개요를 설명하세요.

# 2. 프로젝트 제안서

프로젝트 제안서를 설명하세요. 발표자료와 동영상을 추가하세요. 시스템의 아키텍쳐 설명도 추가하세요.

   <img src="ppt.jpg"/>[발표자료](/project.pptx)<br>
   <iframe id="ytplayer" type="text/html" width="640" height="360" src="https://www.youtube.com/embed/6LxbdIjWP04" frameborder="0"></iframe>
 

# 3. 프로젝트 결과
프로젝트 결과를 추가하세요. 발표자료. 시연동영상을 추가하세요.

## 발표 ppt 
   <img src="ppt.jpg"/>[발표자료](/project.pptx)<br>

## 시연 동영상 

   <iframe id="ytplayer" type="text/html" width="640" height="360" src="https://www.youtube.com/embed/6LxbdIjWP04" frameborder="0"></iframe>

# 4. 본인 소개

본인 소개를 추가하세요

|이름 |고길동|![gdKO](/gdko.jpg)|
|연락처 | gdko(@)kopo.ac.kr|
|skill set| Frontend - HTML, CSS, Javascript|
| | Backend - Java, Spring, Oracle|
|자격증|  |
|수상| |
|특기사항|  TOEIC 990 |

# 5. 기타

### ❤ 데이터 베이스 구축과정
<details>
  <summary style="">
     자세히보기
  </summary>
<div markdown="1">

> ## ✔개발환경
>  - python, jupyther notebook

 ## 😎아파트 기본정보
```python
import urllib.request
from bs4 import BeautifulSoup
import time
import pandas as pd
from pandas import DataFrame as df
import openpyxl

def getaptdata(loadCode):
    key = "-"
    url = "http://apis.data.go.kr/1611000/AptListService/getLegaldongAptList?bjdCode="+loadCode+"&serviceKey="+key
    try:
        f = urllib.request.urlopen(url)
    except Exception as e:
        print('Fail ' + str(e))
        time.sleep(100)
        f = urllib.request.urlopen(url)


    aptxml = f.read().decode("utf8")
    f.close()
    soup = BeautifulSoup(aptxml, "lxml")
    aptdata = []
    for item in soup.find_all("item"):
        aptdataAll = [loadCode, item.find("kaptcode").get_text(), item.find("kaptname").get_text()]
        aptdata.append(aptdataAll)
        print(aptdata)
    return(aptdata)

apt = []
wb = openpyxl.load_workbook('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/법정동코드.xlsx')
ws = wb['법정동코드 전체자료']
print("test")
cells = ws['A2':'A20525']

workbook = openpyxl.Workbook()
sheet = workbook.active
sheet.append(["bjdCode", "kaptCode","kaptName"])
for row in cells :
    for cell in row:
        print(cell.value)
        test = getaptdata(str(cell.value))
        if test :
            for x,y,z in test :
                check = [x,y,z]
                print(check)
                sheet.append(check)
            workbook.save('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/아파트기본정보.xlsx')
print("finish")
```

   
## 😎아파트 상세정보

```python  
import urllib.request
from bs4 import BeautifulSoup
import time
import pandas as pd
from pandas import DataFrame as df
import openpyxl

def getaptdata(kaptCode):
    key = "openAPI KEY"
    url = "http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo?kaptCode="+kaptCode+"&serviceKey="+key
    try:
        f = urllib.request.urlopen(url)
    except Exception as e:
        print('Fail ' + str(e))
        time.sleep(100)
        f = urllib.request.urlopen(url)


    aptxml = f.read().decode("utf8")
    f.close()
    soup = BeautifulSoup(aptxml, "lxml")
    item = soup.find("item")
    aptdataAll = []
    aptSearch = ["bjdcode", "codehallnm", "codeheatnm", "codesalenm","hocnt", "kaptacompany", "kaptaddr", "kaptbcompany"
                 , "kaptcode", "kaptdongcnt", "kaptfax", "kaptmarea", "kaptmparea_135", "kaptmparea_136", "kaptmparea_60"
                 , "kaptmparea_85", "kaptname", "kapttarea", "kaptdacnt", "privarea", "kapturl", "dorojuso", "codeaptnm"
                 , "codemgrnm", "kapttel", "kaptusedate"]

    for aptOne in aptSearch :
        try:
            aptdataAll.append(item.find(aptOne).get_text())
        except AttributeError as e:
            aptdataAll.append('-')
            pass


    return(aptdataAll)

apt = []
wb = openpyxl.load_workbook('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/2.아파트기본정보.xlsx')
ws = wb['Sheet']
cells = ws['B2':'B11340']

workbook = openpyxl.Workbook()
sheet = workbook.active
sheet.append(["bjdcode", "codehallnm" ,"codeheatnm" 
              ,"codesalenm","hocnt"
              ,"kaptacompany","kaptaddr","kaptbcompany", "kaptcode","kaptdongcnt","kaptfax",
              "kaptmarea","kaptmparea_135","kaptmparea_136","kaptmparea_60",
              "kaptmparea_85","kaptname","kapttarea","kaptdacnt","privarea",
              "kapturl","dorojuso","codeaptnm","codemgrnm","kapttel","kaptusedate"])

for row in cells :
    for cell in row:
        aptInfoAll = getaptdata(str(cell.value))
        if aptInfoAll :
            print(aptInfoAll)
            sheet.append(aptInfoAll)
            workbook.save('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/아파트상세정보.xlsx')
```

## 😎아파트 LAT LNG
```python
# 아파트 상세 정보 출력 코드
import urllib.request
from bs4 import BeautifulSoup
import time
import pandas as pd
from pandas import DataFrame as df
import openpyxl

def getLatLng(addr) :
    url = 'https://dapi.kakao.com/v2/local/search/address.json?query=' + addr
    headers = {"Authorization": "KakaoAK 354135ccdb89653ab5ecb933d96d903a"}
    global latlng
    try:
        result = json.loads(str(requests.get(url, headers=headers).text))
        latlng = []

    except Exception as e:
        print('Fail ' + str(e))
        time.sleep(100)
        result = json.loads(str(requests.get(url, headers=headers).text))
    try:
        match_first = result['documents'][0]['address']
        latlng =[float(match_first['y']), float(match_first['x'])]
    except IndexError as e:
        latlng=['-','-']
        
    return latlng

apt = []
wb = openpyxl.load_workbook('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/3.아파트상세정보.xlsx')
ws = wb['Sheet']
cells = ws['B2':'G11340']

workbook = openpyxl.Workbook()
sheet = workbook.active
sheet.append(["lat","lan"])

latlngSave=""
for row in cells :
    for cell in row:
        print(cell.value)
        latlngSave = getLatLng(str(cell.value))
        if latlngSave :
            sheet.append(latlngSave)
            workbook.save('C:/Lecture/프로젝트/최종프로젝트/데이터베이스구축/아파트상세정보-위경도.xlsx')
print("finish")
```

</div>
</details>

### ❤ api key값 properties파일로 관리
<details>
   <summary style="">
      자세히보기
   </summary>
 <div markdown="1">
   
#### 1. properties파일을 만든다. __[src/main/resources/config/properties/key.properties]__
&nbsp; ![properties1](./githubimg/properties1.png) <br>
&nbsp; __[key.properties]파일 내부__ <br>
&nbsp; ![properties2](./githubimg/properties2.png)

#### 2. spring-mvc 파일을 수정한다. __[src/main/resources/config/spring/spring-mvc.xml]__
&nbsp; ![properties3](./githubimg/properties3.png)

#### 3. Properties안에 들어있는 값을 사용하려는 jsp의 상단에 taglib 추가한다. 
&nbsp; ![properties4](./githubimg/properties4.png)

#### 4. 다음과 같이 사용하려는 위치에서 <spring:eval></spring:eval>을 사용한다.
&nbsp; ![properties5](./githubimg/properties5.png)
&nbsp; or
&nbsp; ![properties6](./githubimg/properties6.png)

</div>
</details>
