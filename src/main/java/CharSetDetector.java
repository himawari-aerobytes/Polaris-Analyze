import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class CharSetDetector {
    private String charset;
    public CharSetDetector(InputStream is) throws IOException {
        UniversalDetector detector = new UniversalDetector(null);

        byte***REMOVED******REMOVED*** buf = new byte***REMOVED***4096***REMOVED***;

        int nread;
        while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
      ***REMOVED***

        //推測結果を取得する
        detector.dataEnd();
        final String detectedCharset = detector.getDetectedCharset();

        detector.reset();

        if (detectedCharset != null) {
            this.charset =  Charset.forName(detectedCharset).toString();
            return;
      ***REMOVED***
        //文字コードを取得できなかった場合、環境のデフォルトを使用する
        this.charset = Charset.forName(System.getProperty("file.encoding")).toString();
  ***REMOVED***

    public String getCharset() {
        return charset;
  ***REMOVED***
}


