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
<details>
<summary>데이터 베이스 구축과정</summary>
<div markdown="1">
   <details>
   <summary>아파트 상세정보</summary>
   <div markdown="1">
```python 

#아파트 상세화면 
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
   </div>
   </detail
</div>
</details>
 
