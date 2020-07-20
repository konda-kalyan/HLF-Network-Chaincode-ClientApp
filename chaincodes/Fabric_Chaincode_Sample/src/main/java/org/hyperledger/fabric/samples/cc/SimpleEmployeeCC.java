package org.hyperledger.fabric.samples.cc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Java implementation of the OCIO Contract 
 */
@Contract(
        name = "simple_java_employee_cc",
        info = @Info(
                title = "simple_java_employee_cc",
                description = "simple_java_employee_cc",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "kkonda3@example.com",
                        name = "simple_java_employee_cc",
                        url = "https://www.example.com/")))

@Default
public final class SimpleEmployeeCC implements ContractInterface {

    private enum EmployeeErrors {
        EMPLOYEE_NOT_FOUND,
        EMPLOYEE_ALREADY_EXISTS
    }

    /**
     * Creates a new Employee on the ledger.
     *
     * @param ctx the transaction context
     * @param key the key for the new Employee
     * @param Employee JSON String
     * @return the stored Employee
     */
    @Transaction()
    public String addEmployee(final Context ctx, String key, String emplyoeeString) throws Exception {
        ChaincodeStub stub = ctx.getStub();
        String employeeStateString = stub.getStringState(key);
        if (!employeeStateString.isEmpty()) {
            String errorMessage = String.format("addEmployee: Employee %s already exists", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, EmployeeErrors.EMPLOYEE_ALREADY_EXISTS.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
    	Employee employee = mapper.readValue(emplyoeeString, Employee.class);

        
        String responseString = mapper.writeValueAsString(employee);
        
        stub.putStringState(key, responseString);	// *** Ledger call

        return responseString;
    } // addEmployee transaction ends

    
    /**
     * Retrieves a Employee with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @return the Employee found on the ledger if there was one
     */
    @Transaction()
    public String queryEmployee(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String employeeStateString = stub.getStringState(key);	// *** Ledger call

        if (employeeStateString.isEmpty()) {
            String errorMessage = String.format("queryEmployee: Employee %s does not exist", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, EmployeeErrors.EMPLOYEE_NOT_FOUND.toString());
        }

        return employeeStateString;
    }
}
