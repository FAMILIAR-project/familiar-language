package be.ac.fundp.info.TVLParser.symbolTables;

import java.util.HashMap;
import java.util.Map;

public class RecordSymbol extends AttributeSymbol implements TypeSymbol {
	
	private String id, userType;
	private int type; 
	private RecordSymbol typeDefinition;
	
	private Map<String, AttributeSymbol> attributeSymbols;
	private Map<String, AttributeSymbol> allAttributeSymbols;
		
	public RecordSymbol(String userType, int type, String id, Map<String, AttributeSymbol> attributeSymbols, RecordSymbol recordType) {		
		this.userType = userType;
		this.id = id;
		this.attributeSymbols = attributeSymbols;
		this.type = type;
		this.typeDefinition = recordType;
		
		//merge attributes from the type and from the attribute declaration 
		allAttributeSymbols = this.mergeAttributeSymbols();		
	}
	
	public RecordSymbol(String userType, int type, String id, Map<String, AttributeSymbol> attributeSymbols) {
		this(userType, type, id, attributeSymbols, null);
	}
	
	public RecordSymbol(int type, String id, Map<String, AttributeSymbol> attributeSymbols) {
		this(null, type, id, attributeSymbols, null);
	}
	
	public boolean isARecordSymbol() {
		return true;
	}
	
	public AttributeSymbol getAttributeSymbol(String attributeSymbolID) {
		return this.attributeSymbols.get(attributeSymbolID);
	}
	
	public Map<String, AttributeSymbol> getAttributeSymbols() {
		return this.attributeSymbols;
	}
	
	public AttributeSymbol getMergedAttributeSymbol(String attributeSymbolID) {
		return this.allAttributeSymbols.get(attributeSymbolID);
	}
	
	private Map<String, AttributeSymbol> mergeAttributeSymbols() {
		
		if (attributeSymbols == null) attributeSymbols = new HashMap<String, AttributeSymbol>();
		
		Map<String, AttributeSymbol> allAttributes = new HashMap<String, AttributeSymbol>();
		
		if (typeDefinition != null){
			//init with all sub-attributes defined in the STRUCT
			allAttributes.putAll(typeDefinition.getAttributeSymbols());
		}		
		
		//override with values of sub-attributes defined in sub-attributes declaration
		allAttributes.putAll(attributeSymbols);
		
		return allAttributes;
		
	}
	
	public String getID() {
		return this.id;
	}
	
	public int getType() {
		return this.type;
	}
	
	public boolean containsRecordField(String recordFieldID) {
		return this.attributeSymbols.containsKey(recordFieldID);
	}
	
	public void printAttribute(String espace) {
		System.out.println(espace+"  |      "+this.userType+" struct "+this.id+" {"); 
		int i = 0;
		Object[] keys= attributeSymbols.keySet().toArray();
		while (i <= attributeSymbols.size()-1) {
			attributeSymbols.get(keys[i].toString()).printAttribute("  "+espace);
			i++;
		}
		System.out.println(espace+"  |      }"); 
		
		System.out.println(espace + "  | 	---- merged attributes (include attributes defined in Type) ----"); 
		
		System.out.println(espace+"  |      "+this.userType+" struct "+this.id+" {"); 
		int j = 0;
		Object[] keys2= allAttributeSymbols.keySet().toArray();
		while (j <= allAttributeSymbols.size()-1) {
			allAttributeSymbols.get(keys2[j].toString()).printAttribute("  "+espace);
			j++;
		}
		System.out.println(espace+"  |      }"); 
		
	}
	
	public String getId() {
		return id;
	}
	public String getUserType() {
		return userType;
	}
	public void print() {
		System.out.println("  struct "+id+" {");
		int i = 0;
		Object[] keys= attributeSymbols.keySet().toArray();
		while (i <= attributeSymbols.size()-1) {
			attributeSymbols.get(keys[i].toString()).printAttribute("");
			i++;
		}
		System.out.println("  }");
	}
	
}
