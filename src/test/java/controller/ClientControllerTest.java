package controller;

import model.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientControllerTest {
    @Test
    public void ValidateClient_whenClientIsValid_expectedNull() {
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("Catalin Istrate","some street, number 100","5");

        // assert statements
        assert(result == null);
    }

    @Test
    public void testValidateClient_whenNameContainsBadChars_expectedBadMessage() {
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("Cat@lin","some street, number 20","5");

        // assert statements
        assert(result.equals("Invalid character: @"));
    }

    @Test
    public void testValidateClient_whenClientIsEmpty_expectedBadMessage(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("","some number some street","5");

        // assert statements
        assert(result.equals("Name or address cannot be empty!"));
    }

    @Test
    public void testValidateClient_whenAddressDoesNotContainStreet_expectedBadMessage(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("catalin","some number some zipcode","5");

        // assert statements
        assert(result.equals("Address does not contain street."));
    }

    @Test
    public void testValidateClient_whenAddressDoesNotContainNumber_expectedBadMessage(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("catalin","some street some zipcode","5");

        // assert statements
        assert(result.equals("Address does not contain number."));
    }

    @Test
    public void testValidateClient_whenAddressDoesNotContainAddress_expectedBadMessage(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("catalin","","5");

        // assert statements

        assert(result.equals("Name or address cannot be empty!"));
    }

    @Test
    public void testValidateClient_whenNameIsTooLong_expectedBadMessage(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "some number some street","5");

        // assert statements
        assert(result.equals("Name is too long."));
    }

    @Test
    public void testValidateClient_whenNameIsMaxLength_expectedNull(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.ValidateClient("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "some number some street","5");

        // assert statements
        assert(result == null);
    }

    @Test
    public void testAddClient_whenIdIsCorrect_expectedNull(){
        ClientController clientController = new ClientController(); // ClientController is tested

        String result = clientController.AddClient("catalin istrate",
                "some number some street","5");

        // assert statements
        assert(result == null);
    }

    @Test
    public void testAddClient_whenIdExists_expectedNull(){
        ClientController clientController = new ClientController(); // ClientController is tested
        clientController.AddClient("catalin istrate",
                "some number some street","5");

        String result = clientController.AddClient("catalin istrate",
                "some number some street","5");

        // assert statements
        assert(result.equals("Client already exists!"));
    }


    //-----------------------------------------------------------------
    //WBT start here
    @Test
    public void testAddClientIndex_whenMonthisNegative_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.AddClientIndex(new Client("asd","asd","asd"),2010,-1,100);

        assert(result.equals("Month can't be 0 or less!"));
    }

    @Test
    public void testAddClientIndex_whenYearIs0_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.AddClientIndex(new Client("asd","asd","asd"),0,9,100);

        assert(result.equals("Year can't be 0 or less!"));
    }

    @Test
    public void testAddClientIndex_whenToPayIsNegative_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.AddClientIndex(new Client("asd","asd","asd"),2018,9,-10);

        assert(result.equals("Money to pay can't be less than 0!"));
    }

    @Test
    public void testAddClientIndex_whenClientIsInvalid_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.AddClientIndex(new Client("asd","asd","asd"),2018,9,10);
        assert(result.equals("Address does not contain number."));
    }

    @Test
    public void testAddClientIndex_whenClientIsNotExisting_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.AddClientIndex(new Client("catalin istrate",
                "some number some street","5"),2018,9,10);
        assert(result.equals("Client does not exist!"));
    }

    @Test
    public void testAddClientIndex_whenInputIsFine_expectedNull(){
        ClientController clientController = new ClientController();
        clientController.AddClient("catalin istrate",
                "some number some street","5");
        String result = clientController.AddClientIndex(new Client("catalin istrate",
                "some number some street","5"),2018,9,10);
        assert(result == null);
    }

    @Test
    public void testAddClientIndex_whenIndexAlreadyExists_expectedMessage(){
        ClientController clientController = new ClientController();
        clientController.AddClient("catalinn istrate",
                "some number some street","51");
        clientController.AddClientIndex(new Client("catalinn istrate",
                "some number some street","51"),2018,9,10);
        String result = clientController.AddClientIndex(new Client("catalinn istrate",
                "some number some street","51"),2018,9,10);
        assert(result.equals("Monthly index already exists!"));
    }

    //BBT for functionality c
    @Test
    public void testListInvoices_whenClientHasNoInvoices_expectedMessage(){
        ClientController clientController = new ClientController();
        String result = clientController.ListIssue(new Client("catalin","some street some number","13"));
        assert(result.equals(""));
    }

    //Integ. tests start here

    @Test
    public void mainIntegrationTestMethod_CaseOne(){
        ClientController clientController = new ClientController();
        String name = "Catalin";
        String address = "some street some number";
        String id = "13";
        String resultA = auxiliaryIntegrationMethodA_CaseOne(clientController,name,address,id);
        String resultB = auxiliaryIntegrationMethodB_CaseOne(clientController,name,address,id);
        String resultC = auxiliaryIntegrationMethodC_CaseOne(clientController,name,address,id);


        assert(resultA == null);
        assert(resultB == null);
        assert(resultC.equals("Year: 2018, Month: 11,ToPay: 100 Paid:  0\n"));

    }

    /*
        This method adds a valid client and returns the result
     */
    private String auxiliaryIntegrationMethodA_CaseOne(ClientController clientController, String name, String address, String id){

        return clientController.AddClient(name, address, id);

    }

    private String auxiliaryIntegrationMethodB_CaseOne(ClientController cc, String name, String address, String id){
        return cc.AddClientIndex(new Client(name,address,id),2018,11,100);
    }

    private String auxiliaryIntegrationMethodC_CaseOne(ClientController cc, String name, String address, String id){
        return cc.ListIssue(new Client(name,address,id));
    }

}