import com.couchbase.client.CouchbaseClient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * User: LEBRUN_G
 * Date: 08/10/13
 * Time: 17:02
 */
public class DataLoader {
  // --------------------------- main() method ---------------------------

  public static void main(String[] args) throws URISyntaxException, IOException {
    CouchbaseClient client = new CouchbaseClient(
            Arrays.asList(new URI("http://127.0.0.1:8091/pools")),
            "oxiane",
            "");

    final String[] strings = {"json"};
    final String path = DataLoader.class.getResource("/regions").getPath();
    for (File file : FileUtils.listFiles(new File(path), strings, false)) {
      client.set("param::region::" + file.getName().split("\\.")[0], FileUtils.readFileToString(file));
    }
    client.set("param::vin::caracteristiques", FileUtils.readFileToString(new File(DataLoader.class.getResource("/caracteristiques.json").getPath())));
  }
}
