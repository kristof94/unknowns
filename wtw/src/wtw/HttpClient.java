package wtw;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClient extends DefaultHttpClient {
	public HttpClient(
            final ClientConnectionManager conman,
            final HttpParams params) {
        super(conman, params);
    }
    
       
    public HttpClient(final HttpParams params) {
        super(null, params);
    }

    
    public HttpClient() {
        super(null, null);
    }

    
    @Override
    protected HttpParams createHttpParams() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, 
                HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, 
                HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, 
                true);
        HttpConnectionParams.setTcpNoDelay(params, 
                true);
        HttpConnectionParams.setSocketBufferSize(params, 
                8192);
        HttpProtocolParams.setUserAgent(params, 
                "MAUI_WAP_Browser");
        return params;
    }
}
