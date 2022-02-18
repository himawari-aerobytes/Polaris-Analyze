# Polaris-Analyze
Polaris-EM のCSVデータ解析用ツールです．

Analyzerがメインクラスになります．
解析するcsvファイルは， source.csvという名前でrootに設置してください．

## 動作環境
- JDK : AmazonCorretto-11
- Windows 10 (macOSでも旧バージョンの動作は確認済み)
- IntelliJ IDEA 2021.1

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

## 導入手順
### IntelliJ IDEA の導入
- JetBrains のアカウントを作成します．
- JetBrains Product Pack for Students のライセンスを発行します．
- IntelliJ IDEA Ultimateをダウンロードします
- インストールをします
- インストール後ライセンス認証を行います

### Amazon Corretto 11 の導入
- https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html
より，お使いの環境に合わせてcorrettoをダウンロードします．
  
- インストールを行います．

- Pathを通します．
  https://docs.aws.amazon.com/ja_jp/corretto/latest/corretto-11-ug/windows-7-install.html
  を参考にしてください
  
### 実行環境の構築
- 実行構成の編集の+ボタンより新規構成を追加します
- SDKにcorretto-11をセットします
- パッケージにPolaris-Analyze.mainを指定します．
- メインクラスにAnalyzerを指定します．
- プログラムの引数にsource.csvを指定します．(PolarisEMの管理画面から得ることのできるCSVファイルの名前)

以上で実行可能な状態です．

GitのWebブランチよりブラウザから既読率の推移やメッセージを表示できます．
この場合はブランチをWebへチェックアウトして，GradleのbootRunを実行してください．
URLはhttp://localhost:8080です．