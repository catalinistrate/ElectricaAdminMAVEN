package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientControllerTest {
    @Test
    public void ValidateClient_whenClientIsValid_expectedNull() {
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("asd","asd","5");

        // assert statements
        assert(result == null);
    }

    @Test
    public void testValidateClient_whenClientIsEmpty_expectedBadMessage() {
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("","","5");

        // assert statements
        assert(result.equals("Name or address cannot be empty!"));
    }

}