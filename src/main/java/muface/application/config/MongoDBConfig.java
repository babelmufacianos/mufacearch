package muface.application.config;

import muface.arch.configuration.ArqMongoDBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "muface.application.domain.repository.customers",
        mongoTemplateRef = "bdMongoTemplate"
)
public class MongoDBConfig extends ArqMongoDBConfig {


}
