package ajbc.project3.dataBase;

import java.util.ArrayList;
import java.util.List;

import ajbc.project3.models.Address;


public class AddressDB {
	
	protected static List<Address> addressList;
	
	public AddressDB ()
	{
		addressList = new ArrayList<Address>();
		initListOfAddress();
	}
	

	private static void initListOfAddress()
	{
		addressList.add(new Address("Israel","Tel Aviv","Rothschild",32));
		addressList.add(new Address("Israel","Tiberias","Hahof",10));
		addressList.add(new Address("Israel","Dead Sea","Ein Gedi",80));
		addressList.add(new Address("Israel","Eilat","Red Canyon",7));
		addressList.add(new Address("Croatia","Zagreb","Tkalciceva",109));
		addressList.add(new Address("Germany","Berlin","Brauereihof",6));
		addressList.add(new Address("Germany","Berlin","Mohrenstrabe",30));
		addressList.add(new Address("England","London","Tavistock Square",55));
		addressList.add(new Address("England","London","Tavistock Square",50));
		
	}
	
	

}
