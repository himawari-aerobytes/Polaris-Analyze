# Polaris-Analyze
Polaris-EM のCSVデータ解析用ツールです．

Analyzerがメインクラスになります．
解析するcsvファイルは， source.csvという名前でrootに設置してください．

## 動作環境
- JDK : AmazonCoretto-11
- Windows 10,macOS BigSurで動作確認
- IntelliJ Idea 2021.1 (Ultimate,学生は無料で利用可能)
- Spring Boot 5.0.7
- thymeleaf 3.0.9


## 必須ファイル
- ソースファイル : PolarisEMのCSVファイル(ファイル名をコマンドライン引数に設定)  
ソースの中身  
  登録日時 String  
  形態 String  
  ヘッドライン String  
  APNs:送信数 String  
  APNs:成功 String  
  APNs:失敗 String  
  GCM:送信数 String  
  GCM:成功 String  
  GCM:失敗 String  
  sender String  
  user String  
  root_push_id String   
  既読状態 Object  
  
既読状態の中身  
String t_device_mng_id  
String status  
String status_name  
String response_result_created  
String device_name  
String user_notes  

- 学生情報ファイル(Members.jsonという名前でルートに配置) : 学生登録情報です．
```json
[
  {
    "name": "学生1",
    "number": "135",
    "grade": "B3"
  },
  {
    "name": "学生2",
    "number": "134",
    "grade": "B4"
  },
  {
    "name": "学生3",
    "number": "135",
    "grade": "M1"
  },  
  {
    "name": "学生4",
    "number": "136",
    "grade": "M2"
  }
]
```

以上の例を参考に作成してください．
学年はB3,B4,M1,M2の様に入力しています．登録情報(jsonファイル)とAnalyzer.javaの
`System.out.println(wadaLab.calcGradePercentage("B3"));`の引数と一致していれば問題ありません．

## 解析期間
解析する期間は、analyzer.logic.analyze.Storeの変数を設定してください。