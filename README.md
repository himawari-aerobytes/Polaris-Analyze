# Polaris-Analyze
Polaris-EM のCSVデータ解析用ツールです．

Analyzerがメインクラスになります．
解析するcsvファイルは， source.csvという名前でrootに設置してください．

## 動作環境
- JDK : AmazonColetto-11
- Windows 10 (macOSでも旧バージョンの動作は確認済み)



## 必須ファイル
- ソースファイル : PolarisEMのCSVファイル(ファイル名をコマンドライン引数に設定)

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
