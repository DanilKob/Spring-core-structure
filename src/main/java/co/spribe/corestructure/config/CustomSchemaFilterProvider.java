package co.spribe.corestructure.config;

import org.hibernate.tool.schema.spi.SchemaFilter;
import org.hibernate.tool.schema.spi.SchemaFilterProvider;

public class CustomSchemaFilterProvider implements SchemaFilterProvider {

    @Override
    public SchemaFilter getCreateFilter() {
        return CustomSchemaFilter.getInstance();
    }

    @Override
    public SchemaFilter getDropFilter() {
        return CustomSchemaFilter.getInstance();
    }

    @Override
    public SchemaFilter getMigrateFilter() {
        return CustomSchemaFilter.getInstance();
    }

    @Override
    public SchemaFilter getValidateFilter() {
        return CustomSchemaFilter.getInstance();
    }
}
