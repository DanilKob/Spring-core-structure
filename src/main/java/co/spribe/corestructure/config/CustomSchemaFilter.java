package co.spribe.corestructure.config;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

public class CustomSchemaFilter implements SchemaFilter {

    private static CustomSchemaFilter instance = new CustomSchemaFilter();

//    @Value("${tables-on-startup-exclude}")
//    private String[] excludeTableNames;
//
//    private Set<String> excludeTableNamesSet;
//
//    @PostConstruct
//    private void setStaticInstance(){
//        instance = this;
//
//        List<String> excludeTableNamesAsArray;
//        if(excludeTableNames != null){
//            excludeTableNamesAsArray = Arrays.asList(excludeTableNames);
//        } else {
//            excludeTableNamesAsArray = new LinkedList<>();
//        }
//
//        excludeTableNamesSet = new HashSet<>(excludeTableNamesAsArray);
//    }

    public static CustomSchemaFilter getInstance() {
        return instance;
    }

    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {

        if (table.getName().toLowerCase().equals("ping")) {
            return false;
        }

        return true;
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}
