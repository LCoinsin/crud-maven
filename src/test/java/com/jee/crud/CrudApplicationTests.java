package com.jee.crud;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import com.jee.crud.repository.CommandRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;


import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class CrudApplicationTests {
    @Autowired
    private CommandRepository commandRepository;

/*    @Test
    public void testApi() {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given().when().get("/books");

        // Vérifie que le code de statut de la réponse est 200
        Assert.assertEquals(200, response.getStatusCode());

        // Vérifie que le contenu de la réponse est bien du JSON
        response.then().assertThat().contentType("application/json");

        // Vérifie que la réponse contient une clé spécifique avec une valeur attendue
        response.then().assertThat().body("isbn", equalTo("\\d+"));
    }*/

    @Test
    public void testApi() throws IOException {
        String apiUrl = "http://localhost:8080/books";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide

        
        assertEquals("[{\"isbn\":1,\"title\":\"diam dictum sapien. Aenean\",\"publication\":\"05/06/02\",\"price\":45,\"author\":{\"id\":23,\"name\":\"Palmer Bakhuizen\",\"phone\":\"08 11 31 42 41\",\"email\":\"commodo.hendrerit@outlook.couk\",\"address\":\"Ap #116-9936 Eu Rd.\",\"country\":\"Belgium\",\"city\":\"Sotteville-ls-Rouen\"},\"genre\":\"Fantasy Humour\"},{\"isbn\":2,\"title\":\"quam quis diam. Pellentesque\",\"publication\":\"09/14/22\",\"price\":19,\"author\":{\"id\":18,\"name\":\"Luke \",\"phone\":\"05 35 58 51 13\",\"email\":\"neque@yahoo.com\",\"address\":\"889-7850 Nonummy St.\",\"country\":\"France\",\"city\":\"Pau\"},\"genre\":\"Adventure Crime\"},{\"isbn\":3,\"title\":\"est. Nunc laoreet lectus\",\"publication\":\"02/11/03\",\"price\":40,\"author\":{\"id\":14,\"name\":\"Chantale \",\"phone\":\"08 72 16 12 56\",\"email\":\"facilisis.eget@hotmail.org\",\"address\":\"698-1684 Dictum Rd.\",\"country\":\"Belgium\",\"city\":\"Reims\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":4,\"title\":\"sed, hendrerit a, arcu.\",\"publication\":\"04/05/20\",\"price\":20,\"author\":{\"id\":12,\"name\":\"Ulysses van der Bosch\",\"phone\":\"01 69 05 15 56\",\"email\":\"pellentesque.a@icloud.org\",\"address\":\"875-1836 Ut St.\",\"country\":\"Belgium\",\"city\":\"Quenast\"},\"genre\":\"Humour Horror\"},{\"isbn\":5,\"title\":\"consectetuer adipiscing elit. Etiam\",\"publication\":\"07/18/18\",\"price\":41,\"author\":{\"id\":12,\"name\":\"Ulysses van der Bosch\",\"phone\":\"01 69 05 15 56\",\"email\":\"pellentesque.a@icloud.org\",\"address\":\"875-1836 Ut St.\",\"country\":\"Belgium\",\"city\":\"Quenast\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":6,\"title\":\"hendrerit. Donec porttitor tellus\",\"publication\":\"09/22/11\",\"price\":10,\"author\":{\"id\":19,\"name\":\"Jonah \",\"phone\":\"06 15 32 12 68\",\"email\":\"dui.semper.et@protonmail.ca\",\"address\":\"5009 Magnis St.\",\"country\":\"Belgium\",\"city\":\"Dole\"},\"genre\":\"Fantasy Humour\"},{\"isbn\":7,\"title\":\"lorem fringilla ornare placerat,\",\"publication\":\"05/02/20\",\"price\":34,\"author\":{\"id\":9,\"name\":\"Quin \",\"phone\":\"09 27 29 68 52\",\"email\":\"curabitur.dictum@aol.com\",\"address\":\"578-5363 Tellus. St.\",\"country\":\"Belgium\",\"city\":\"Vannes\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":8,\"title\":\"elementum sem, vitae aliquam\",\"publication\":\"06/17/08\",\"price\":7,\"author\":{\"id\":3,\"name\":\"Yvette \",\"phone\":\"07 56 05 44 74\",\"email\":\"at.augue@aol.org\",\"address\":\"P.O. Box 761, 5634 Quam. Ave\",\"country\":\"France\",\"city\":\"Strasbourg\"},\"genre\":\"Fantasy Humour\"},{\"isbn\":9,\"title\":\"at sem molestie sodales.\",\"publication\":\"09/03/10\",\"price\":5,\"author\":{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},\"genre\":\"Humour Horror\"},{\"isbn\":10,\"title\":\"vitae erat vel pede\",\"publication\":\"05/09/03\",\"price\":6,\"author\":{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},\"genre\":\"Humour Horror\"},{\"isbn\":11,\"title\":\"Nunc mauris elit, dictum\",\"publication\":\"05/09/07\",\"price\":12,\"author\":{\"id\":20,\"name\":\"Gavin van der Graaf\",\"phone\":\"06 75 85 86 30\",\"email\":\"nostra@aol.org\",\"address\":\"Ap #483-7945 Ultrices Road\",\"country\":\"France\",\"city\":\"Troyes\"},\"genre\":\"Crime Historical\"},{\"isbn\":12,\"title\":\"Maecenas malesuada fringilla est.\",\"publication\":\"07/26/00\",\"price\":5,\"author\":{\"id\":7,\"name\":\"Damian Archambault\",\"phone\":\"04 87 64 47 92\",\"email\":\"interdum.libero.dui@protonmail.com\",\"address\":\"3301 Nonummy St.\",\"country\":\"Belgium\",\"city\":\"Vierzon\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":13,\"title\":\"placerat. Cras dictum ultricies\",\"publication\":\"11/10/12\",\"price\":46,\"author\":{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},\"genre\":\"Crime Historical\"},{\"isbn\":14,\"title\":\"semper pretium neque. Morbi\",\"publication\":\"12/28/17\",\"price\":4,\"author\":{\"id\":24,\"name\":\"Raya Timmermans\",\"phone\":\"08 41 10 44 38\",\"email\":\"cursus.vestibulum@protonmail.com\",\"address\":\"686-1744 Lectus Road\",\"country\":\"Belgium\",\"city\":\"Vertou\"},\"genre\":\"Crime Historical\"},{\"isbn\":15,\"title\":\"dui, nec tempus mauris\",\"publication\":\"07/31/19\",\"price\":44,\"author\":{\"id\":16,\"name\":\"Axel Dijkstra\",\"phone\":\"03 15 78 51 82\",\"email\":\"lorem.vitae@google.couk\",\"address\":\"Ap #225-1995 Eros Av.\",\"country\":\"France\",\"city\":\"Tintigny\"},\"genre\":\"Humour Horror\"},{\"isbn\":16,\"title\":\"Duis gravida. Praesent eu\",\"publication\":\"10/26/05\",\"price\":39,\"author\":{\"id\":19,\"name\":\"Jonah \",\"phone\":\"06 15 32 12 68\",\"email\":\"dui.semper.et@protonmail.ca\",\"address\":\"5009 Magnis St.\",\"country\":\"Belgium\",\"city\":\"Dole\"},\"genre\":\"Adventure Crime\"},{\"isbn\":17,\"title\":\"a feugiat tellus lorem\",\"publication\":\"06/18/23\",\"price\":9,\"author\":{\"id\":22,\"name\":\"Elton \",\"phone\":\"01 72 88 35 40\",\"email\":\"pharetra.ut@hotmail.ca\",\"address\":\"248-2866 Erat Avenue\",\"country\":\"Belgium\",\"city\":\"Sint-Amandsberg\"},\"genre\":\"Humour Horror\"},{\"isbn\":18,\"title\":\"Class aptent taciti sociosqu\",\"publication\":\"06/11/06\",\"price\":5,\"author\":{\"id\":11,\"name\":\"Zena Spijker\",\"phone\":\"06 35 42 54 74\",\"email\":\"elit.erat@yahoo.ca\",\"address\":\"440-3482 Vestibulum St.\",\"country\":\"Belgium\",\"city\":\"Alenon\"},\"genre\":\"Adventure Crime\"},{\"isbn\":19,\"title\":\"est. Mauris eu turpis.\",\"publication\":\"08/26/01\",\"price\":28,\"author\":{\"id\":13,\"name\":\"Gloria Rademaker\",\"phone\":\"02 37 74 68 24\",\"email\":\"erat.vivamus@outlook.com\",\"address\":\"P.O. Box 364, 9122 Eget, St.\",\"country\":\"Belgium\",\"city\":\"Bastia\"},\"genre\":\"Crime Historical\"},{\"isbn\":20,\"title\":\"arcu. Morbi sit amet\",\"publication\":\"08/24/15\",\"price\":18,\"author\":{\"id\":7,\"name\":\"Damian Archambault\",\"phone\":\"04 87 64 47 92\",\"email\":\"interdum.libero.dui@protonmail.com\",\"address\":\"3301 Nonummy St.\",\"country\":\"Belgium\",\"city\":\"Vierzon\"},\"genre\":\"Classics Adventure\"},{\"isbn\":21,\"title\":\"sit amet lorem semper\",\"publication\":\"04/18/22\",\"price\":33,\"author\":{\"id\":24,\"name\":\"Raya Timmermans\",\"phone\":\"08 41 10 44 38\",\"email\":\"cursus.vestibulum@protonmail.com\",\"address\":\"686-1744 Lectus Road\",\"country\":\"Belgium\",\"city\":\"Vertou\"},\"genre\":\"Classics Adventure\"},{\"isbn\":22,\"title\":\"mauris. Morbi non sapien\",\"publication\":\"09/12/01\",\"price\":11,\"author\":{\"id\":4,\"name\":\"Anastasia \",\"phone\":\"07 84 92 54 42\",\"email\":\"facilisis.non@hotmail.org\",\"address\":\"695-635 Nisi. Road\",\"country\":\"Belgium\",\"city\":\"Corroy-le-Chteau\"},\"genre\":\"Horror Romance\"},{\"isbn\":23,\"title\":\"montes, nascetur ridiculus mus.\",\"publication\":\"09/04/07\",\"price\":33,\"author\":{\"id\":5,\"name\":\"Berk Spijker\",\"phone\":\"08 85 35 38 31\",\"email\":\"lacus.nulla@hotmail.org\",\"address\":\"Ap #552-5261 Non, Avenue\",\"country\":\"France\",\"city\":\"Compigne\"},\"genre\":\"Fantasy Humour\"},{\"isbn\":24,\"title\":\"ligula. Donec luctus aliquet\",\"publication\":\"02/17/14\",\"price\":14,\"author\":{\"id\":9,\"name\":\"Quin \",\"phone\":\"09 27 29 68 52\",\"email\":\"curabitur.dictum@aol.com\",\"address\":\"578-5363 Tellus. St.\",\"country\":\"Belgium\",\"city\":\"Vannes\"},\"genre\":\"Fantasy Humour\"},{\"isbn\":25,\"title\":\"ornare egestas ligula. Nullam\",\"publication\":\"06/18/17\",\"price\":39,\"author\":{\"id\":17,\"name\":\"Madaline de Jonge\",\"phone\":\"05 72 47 59 10\",\"email\":\"morbi.vehicula@google.edu\",\"address\":\"Ap #765-6281 A St.\",\"country\":\"Belgium\",\"city\":\"Daknam\"},\"genre\":\"Adventure Crime\"},{\"isbn\":26,\"title\":\"ante blandit viverra. Donec\",\"publication\":\"10/25/00\",\"price\":27,\"author\":{\"id\":9,\"name\":\"Quin \",\"phone\":\"09 27 29 68 52\",\"email\":\"curabitur.dictum@aol.com\",\"address\":\"578-5363 Tellus. St.\",\"country\":\"Belgium\",\"city\":\"Vannes\"},\"genre\":\"Classics Adventure\"},{\"isbn\":27,\"title\":\"ullamcorper, velit in aliquet\",\"publication\":\"10/13/10\",\"price\":38,\"author\":{\"id\":21,\"name\":\"Aphrodite \",\"phone\":\"05 63 75 63 08\",\"email\":\"tempor.bibendum@icloud.net\",\"address\":\"Ap #717-1130 Tellus Ave\",\"country\":\"Belgium\",\"city\":\"Orp-Jauche\"},\"genre\":\"Horror Romance\"},{\"isbn\":28,\"title\":\"in, cursus et, eros.\",\"publication\":\"05/07/10\",\"price\":27,\"author\":{\"id\":10,\"name\":\"Hayden \",\"phone\":\"07 45 52 27 29\",\"email\":\"eget.odio@hotmail.net\",\"address\":\"P.O. Box 341, 9923 Volutpat. Avenue\",\"country\":\"France\",\"city\":\"Noduwez\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":29,\"title\":\"non, sollicitudin a, malesuada\",\"publication\":\"04/11/19\",\"price\":26,\"author\":{\"id\":13,\"name\":\"Gloria Rademaker\",\"phone\":\"02 37 74 68 24\",\"email\":\"erat.vivamus@outlook.com\",\"address\":\"P.O. Box 364, 9122 Eget, St.\",\"country\":\"Belgium\",\"city\":\"Bastia\"},\"genre\":\"Classics Adventure\"},{\"isbn\":30,\"title\":\"convallis, ante lectus convallis\",\"publication\":\"09/21/08\",\"price\":2,\"author\":{\"id\":20,\"name\":\"Gavin van der Graaf\",\"phone\":\"06 75 85 86 30\",\"email\":\"nostra@aol.org\",\"address\":\"Ap #483-7945 Ultrices Road\",\"country\":\"France\",\"city\":\"Troyes\"},\"genre\":\"Horror Romance\"},{\"isbn\":31,\"title\":\"egestas. Aliquam fringilla cursus\",\"publication\":\"06/08/20\",\"price\":36,\"author\":{\"id\":21,\"name\":\"Aphrodite \",\"phone\":\"05 63 75 63 08\",\"email\":\"tempor.bibendum@icloud.net\",\"address\":\"Ap #717-1130 Tellus Ave\",\"country\":\"Belgium\",\"city\":\"Orp-Jauche\"},\"genre\":\"Adventure Crime\"},{\"isbn\":32,\"title\":\"Aenean gravida nunc sed\",\"publication\":\"01/08/12\",\"price\":8,\"author\":{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":33,\"title\":\"eu eros. Nam consequat\",\"publication\":\"02/02/05\",\"price\":44,\"author\":{\"id\":8,\"name\":\"Brent de Ruiter\",\"phone\":\"06 27 35 28 00\",\"email\":\"elit.erat.vitae@icloud.edu\",\"address\":\"580-3024 Curabitur Rd.\",\"country\":\"Belgium\",\"city\":\"Meerhout\"},\"genre\":\"Humour Horror\"},{\"isbn\":34,\"title\":\"consequat nec, mollis vitae,\",\"publication\":\"11/24/22\",\"price\":42,\"author\":{\"id\":10,\"name\":\"Hayden \",\"phone\":\"07 45 52 27 29\",\"email\":\"eget.odio@hotmail.net\",\"address\":\"P.O. Box 341, 9923 Volutpat. Avenue\",\"country\":\"France\",\"city\":\"Noduwez\"},\"genre\":\"Adventure Crime\"},{\"isbn\":35,\"title\":\"Curae Phasellus ornare. Fusce\",\"publication\":\"04/06/13\",\"price\":8,\"author\":{\"id\":23,\"name\":\"Palmer Bakhuizen\",\"phone\":\"08 11 31 42 41\",\"email\":\"commodo.hendrerit@outlook.couk\",\"address\":\"Ap #116-9936 Eu Rd.\",\"country\":\"Belgium\",\"city\":\"Sotteville-ls-Rouen\"},\"genre\":\"Classics Adventure\"},{\"isbn\":36,\"title\":\"massa. Quisque porttitor eros\",\"publication\":\"12/01/02\",\"price\":1,\"author\":{\"id\":24,\"name\":\"Raya Timmermans\",\"phone\":\"08 41 10 44 38\",\"email\":\"cursus.vestibulum@protonmail.com\",\"address\":\"686-1744 Lectus Road\",\"country\":\"Belgium\",\"city\":\"Vertou\"},\"genre\":\"Historical Fantasy\"},{\"isbn\":37,\"title\":\"penatibus et magnis dis\",\"publication\":\"02/18/03\",\"price\":24,\"author\":{\"id\":2,\"name\":\"Zenia \",\"phone\":\"06 96 27 17 11\",\"email\":\"accumsan@protonmail.ca\",\"address\":\"9224 Commodo Road\",\"country\":\"France\",\"city\":\"Lunel\"},\"genre\":\"Adventure Crime\"},{\"isbn\":38,\"title\":\"nisi. Aenean eget metus.\",\"publication\":\"03/23/12\",\"price\":18,\"author\":{\"id\":19,\"name\":\"Jonah \",\"phone\":\"06 15 32 12 68\",\"email\":\"dui.semper.et@protonmail.ca\",\"address\":\"5009 Magnis St.\",\"country\":\"Belgium\",\"city\":\"Dole\"},\"genre\":\"Crime Historical\"},{\"isbn\":39,\"title\":\"vulputate dui, nec tempus\",\"publication\":\"01/19/01\",\"price\":31,\"author\":{\"id\":12,\"name\":\"Ulysses van der Bosch\",\"phone\":\"01 69 05 15 56\",\"email\":\"pellentesque.a@icloud.org\",\"address\":\"875-1836 Ut St.\",\"country\":\"Belgium\",\"city\":\"Quenast\"},\"genre\":\"Humour Horror\"},{\"isbn\":40,\"title\":\"nec ante. Maecenas mi\",\"publication\":\"06/18/02\",\"price\":11,\"author\":{\"id\":25,\"name\":\"Clinton Lachapelle\",\"phone\":\"02 53 53 45 37\",\"email\":\"vivamus.euismod.urna@aol.com\",\"address\":\"266-3129 Est Ave\",\"country\":\"France\",\"city\":\"Doel\"},\"genre\":\"Fantasy Humour\"}]", jsonResponse);
        
        System.out.println("Le test book réussi !");

    }
    
    @Test
    public void testApiAuthor() throws IOException {
        String apiUrl = "http://localhost:8080/author";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        
        	assertEquals("[{\"id\":1,\"name\":\"Burke Hendrix\",\"phone\":\"05 84 87 05 40\",\"email\":\"eu.elit@aol.net\",\"address\":\"5452 In Ave\",\"country\":\"Belgium\",\"city\":\"Westrem\"},{\"id\":2,\"name\":\"Zenia \",\"phone\":\"06 96 27 17 11\",\"email\":\"accumsan@protonmail.ca\",\"address\":\"9224 Commodo Road\",\"country\":\"France\",\"city\":\"Lunel\"},{\"id\":3,\"name\":\"Yvette \",\"phone\":\"07 56 05 44 74\",\"email\":\"at.augue@aol.org\",\"address\":\"P.O. Box 761, 5634 Quam. Ave\",\"country\":\"France\",\"city\":\"Strasbourg\"},{\"id\":4,\"name\":\"Anastasia \",\"phone\":\"07 84 92 54 42\",\"email\":\"facilisis.non@hotmail.org\",\"address\":\"695-635 Nisi. Road\",\"country\":\"Belgium\",\"city\":\"Corroy-le-Chteau\"},{\"id\":5,\"name\":\"Berk Spijker\",\"phone\":\"08 85 35 38 31\",\"email\":\"lacus.nulla@hotmail.org\",\"address\":\"Ap #552-5261 Non, Avenue\",\"country\":\"France\",\"city\":\"Compigne\"},{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},{\"id\":7,\"name\":\"Damian Archambault\",\"phone\":\"04 87 64 47 92\",\"email\":\"interdum.libero.dui@protonmail.com\",\"address\":\"3301 Nonummy St.\",\"country\":\"Belgium\",\"city\":\"Vierzon\"},{\"id\":8,\"name\":\"Brent de Ruiter\",\"phone\":\"06 27 35 28 00\",\"email\":\"elit.erat.vitae@icloud.edu\",\"address\":\"580-3024 Curabitur Rd.\",\"country\":\"Belgium\",\"city\":\"Meerhout\"},{\"id\":9,\"name\":\"Quin \",\"phone\":\"09 27 29 68 52\",\"email\":\"curabitur.dictum@aol.com\",\"address\":\"578-5363 Tellus. St.\",\"country\":\"Belgium\",\"city\":\"Vannes\"},{\"id\":10,\"name\":\"Hayden \",\"phone\":\"07 45 52 27 29\",\"email\":\"eget.odio@hotmail.net\",\"address\":\"P.O. Box 341, 9923 Volutpat. Avenue\",\"country\":\"France\",\"city\":\"Noduwez\"},{\"id\":11,\"name\":\"Zena Spijker\",\"phone\":\"06 35 42 54 74\",\"email\":\"elit.erat@yahoo.ca\",\"address\":\"440-3482 Vestibulum St.\",\"country\":\"Belgium\",\"city\":\"Alenon\"},{\"id\":12,\"name\":\"Ulysses van der Bosch\",\"phone\":\"01 69 05 15 56\",\"email\":\"pellentesque.a@icloud.org\",\"address\":\"875-1836 Ut St.\",\"country\":\"Belgium\",\"city\":\"Quenast\"},{\"id\":13,\"name\":\"Gloria Rademaker\",\"phone\":\"02 37 74 68 24\",\"email\":\"erat.vivamus@outlook.com\",\"address\":\"P.O. Box 364, 9122 Eget, St.\",\"country\":\"Belgium\",\"city\":\"Bastia\"},{\"id\":14,\"name\":\"Chantale \",\"phone\":\"08 72 16 12 56\",\"email\":\"facilisis.eget@hotmail.org\",\"address\":\"698-1684 Dictum Rd.\",\"country\":\"Belgium\",\"city\":\"Reims\"},{\"id\":15,\"name\":\"Graiden van Rijn\",\"phone\":\"01 14 07 83 33\",\"email\":\"integer.urna@hotmail.couk\",\"address\":\"143-6313 Mus. Av.\",\"country\":\"Belgium\",\"city\":\"Houtave\"},{\"id\":16,\"name\":\"Axel Dijkstra\",\"phone\":\"03 15 78 51 82\",\"email\":\"lorem.vitae@google.couk\",\"address\":\"Ap #225-1995 Eros Av.\",\"country\":\"France\",\"city\":\"Tintigny\"},{\"id\":17,\"name\":\"Madaline de Jonge\",\"phone\":\"05 72 47 59 10\",\"email\":\"morbi.vehicula@google.edu\",\"address\":\"Ap #765-6281 A St.\",\"country\":\"Belgium\",\"city\":\"Daknam\"},{\"id\":18,\"name\":\"Luke \",\"phone\":\"05 35 58 51 13\",\"email\":\"neque@yahoo.com\",\"address\":\"889-7850 Nonummy St.\",\"country\":\"France\",\"city\":\"Pau\"},{\"id\":19,\"name\":\"Jonah \",\"phone\":\"06 15 32 12 68\",\"email\":\"dui.semper.et@protonmail.ca\",\"address\":\"5009 Magnis St.\",\"country\":\"Belgium\",\"city\":\"Dole\"},{\"id\":20,\"name\":\"Gavin van der Graaf\",\"phone\":\"06 75 85 86 30\",\"email\":\"nostra@aol.org\",\"address\":\"Ap #483-7945 Ultrices Road\",\"country\":\"France\",\"city\":\"Troyes\"},{\"id\":21,\"name\":\"Aphrodite \",\"phone\":\"05 63 75 63 08\",\"email\":\"tempor.bibendum@icloud.net\",\"address\":\"Ap #717-1130 Tellus Ave\",\"country\":\"Belgium\",\"city\":\"Orp-Jauche\"},{\"id\":22,\"name\":\"Elton \",\"phone\":\"01 72 88 35 40\",\"email\":\"pharetra.ut@hotmail.ca\",\"address\":\"248-2866 Erat Avenue\",\"country\":\"Belgium\",\"city\":\"Sint-Amandsberg\"},{\"id\":23,\"name\":\"Palmer Bakhuizen\",\"phone\":\"08 11 31 42 41\",\"email\":\"commodo.hendrerit@outlook.couk\",\"address\":\"Ap #116-9936 Eu Rd.\",\"country\":\"Belgium\",\"city\":\"Sotteville-ls-Rouen\"},{\"id\":24,\"name\":\"Raya Timmermans\",\"phone\":\"08 41 10 44 38\",\"email\":\"cursus.vestibulum@protonmail.com\",\"address\":\"686-1744 Lectus Road\",\"country\":\"Belgium\",\"city\":\"Vertou\"},{\"id\":25,\"name\":\"Clinton Lachapelle\",\"phone\":\"02 53 53 45 37\",\"email\":\"vivamus.euismod.urna@aol.com\",\"address\":\"266-3129 Est Ave\",\"country\":\"France\",\"city\":\"Doel\"},{\"id\":26,\"name\":\"Lawrence \",\"phone\":\"06 81 57 51 04\",\"email\":\"neque.et@protonmail.edu\",\"address\":\"Ap #261-5281 Egestas. St.\",\"country\":\"Belgium\",\"city\":\"Gomz-Andoumont\"},{\"id\":27,\"name\":\"Oscar Villenueve\",\"phone\":\"03 07 37 44 22\",\"email\":\"et.ipsum@icloud.edu\",\"address\":\"5893 Eget St.\",\"country\":\"Belgium\",\"city\":\"Rouvreux\"},{\"id\":28,\"name\":\"Kuame \",\"phone\":\"03 25 35 78 31\",\"email\":\"duis@outlook.couk\",\"address\":\"Ap #804-1251 Ut Av.\",\"country\":\"Belgium\",\"city\":\"Orvault\"},{\"id\":29,\"name\":\"Brenna van der Velde\",\"phone\":\"06 78 36 93 28\",\"email\":\"amet@yahoo.net\",\"address\":\"Ap #817-1012 Tempor Av.\",\"country\":\"Belgium\",\"city\":\"Saintes\"},{\"id\":30,\"name\":\"Illiana Hoogendoorn\",\"phone\":\"03 44 91 14 56\",\"email\":\"magna.cras.convallis@aol.ca\",\"address\":\"177-7275 Ante. Rd.\",\"country\":\"France\",\"city\":\"Ganshoren\"}]",jsonResponse);
        	System.out.println("Le test Author réussi !");
        	
        }
    

    @Test
    public void testApiCustomer() throws IOException {
        String apiUrl = "http://localhost:8080/customer";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        
        	//assertEquals("", jsonResponse);
         assertEquals("[{\"id\":1,\"name\":\"Flynn Burt\",\"email\":\"amet@aol.fr\",\"address\":\"Ap #325-3166 Faucibus St.\",\"city\":\"Gojal Upper Hunza\",\"country\":\"Spain\"},{\"id\":2,\"name\":\"Kirby Walls\",\"email\":\"tortor.at@protonmail.ca\",\"address\":\"Ap #851-815 Rutrum Street\",\"city\":\"Boryeong\",\"country\":\"Costa Rica\"},{\"id\":3,\"name\":\"Hall Jefferson\",\"email\":\"cum.sociis@protonmail.ca\",\"address\":\"Ap #292-6568 Eu, Ave\",\"city\":\"Als\",\"country\":\"Colombia\"},{\"id\":4,\"name\":\"Aladdin Harper\",\"email\":\"lobortis.quis.pede@yahoo.com\",\"address\":\"6710 Purus. Road\",\"city\":\"Santa Rita\",\"country\":\"Ireland\"},{\"id\":5,\"name\":\"Dahlia Avery\",\"email\":\"amet.diam@aol.uk\",\"address\":\"379-8566 Sed Avenue\",\"city\":\"Adana\",\"country\":\"India\"},{\"id\":6,\"name\":\"Stephanie Mcgee\",\"email\":\"donec.at@aol.fr\",\"address\":\"Ap #197-9850 Quisque Rd.\",\"city\":\"Tacoma\",\"country\":\"Pakistan\"}]", jsonResponse);
        System.out.println("Le test customer réussi !");
        	
        }
    @Test
    public void testApiCommand() throws IOException {
        String apiUrl = "http://localhost:8080/command";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        
        	//assertEquals("", jsonResponse);
        assertEquals("[]", jsonResponse);
         System.out.println("Le test command !");
        	
        }
    
    @Test
    public void testApiStatus() throws IOException {
        String apiUrl = "http://localhost:8080/status";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        
        	//assertEquals("", jsonResponse);
        assertEquals("[{\"id\":1,\"state\":true},{\"id\":3,\"state\":true},{\"id\":4,\"state\":true},{\"id\":5,\"state\":false},{\"id\":6,\"state\":false}]", jsonResponse);
         System.out.println("Le test status !");
        	
        }
    
    @Test
    public void testApiBooksId() throws IOException {
        String apiUrl = "http://localhost:8080/books/1";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        
        	assertEquals("{\"isbn\":1,\"title\":\"diam dictum sapien. Aenean\",\"publication\":\"05/06/02\",\"price\":45,\"author\":{\"id\":23,\"name\":\"Palmer Bakhuizen\",\"phone\":\"08 11 31 42 41\",\"email\":\"commodo.hendrerit@outlook.couk\",\"address\":\"Ap #116-9936 Eu Rd.\",\"country\":\"Belgium\",\"city\":\"Sotteville-ls-Rouen\"},\"genre\":\"Fantasy Humour\"}", jsonResponse);
         System.out.println("Le test books ID !");
        	
        }
    
    @Test
    public void testApiBooksSearch() throws IOException {
        String apiUrl = "http://localhost:8080/books/search?title=vitae%20erat%20vel%20pede&author=Rashad%20Kappel&price=6";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        assertEquals("[{\"isbn\":10,\"title\":\"vitae erat vel pede\",\"publication\":\"05/09/03\",\"price\":6,\"author\":{\"id\":6,\"name\":\"Rashad Kappel\",\"phone\":\"06 02 86 88 98\",\"email\":\"tellus@hotmail.edu\",\"address\":\"Ap #710-7930 Suspendisse Rd.\",\"country\":\"France\",\"city\":\"Saumur\"},\"genre\":\"Humour Horror\"}]", jsonResponse);
       	  System.out.println("Le test books search !");
        	
        }
    @Test
    public void testApiAuthorBurke() throws IOException {
        String apiUrl = "http://localhost:8080/author/Burke%20Hendrix";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
        assertEquals("{\"id\":1,\"name\":\"Burke Hendrix\",\"phone\":\"05 84 87 05 40\",\"email\":\"eu.elit@aol.net\",\"address\":\"5452 In Ave\",\"country\":\"Belgium\",\"city\":\"Westrem\"}", jsonResponse);
        System.out.println("Le test Author Burke !");
        	
        }
    
    @Test
    public void testApiStatusId() throws IOException {
        String apiUrl = "http://localhost:8080/status/1";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Vérifier le code HTTP de la réponse
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode);

        // Lire la réponse et vérifier qu'il s'agit d'un JSON valide
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        String jsonResponse = response.toString();
        // Vérifier qu'il s'agit d'un JSON valide
      assertEquals("{\"id\":1,\"state\":true}", jsonResponse);
       System.out.println("Le test Status Id !");
        	
        }
    
    
    
    
    }
    
    


