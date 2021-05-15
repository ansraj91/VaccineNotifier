package com.utility;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConvertJsonToPojo {

    public static void main(String[] args) {
        String packageName="com.cooltrickshome";
        File inputJson= new File("/Users/anshulkhandelwal/Documents/Personnal/Projects/VaccineNotifier/src/main/resources/reponse.json");
        File outputPojoDirectory=new File("/Users/anshulkhandelwal/Documents/Personnal/Projects/VaccineNotifier/src/main/java/com/response/model");
        outputPojoDirectory.mkdirs();
        try {
            convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Encountered issue while converting to pojo: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
        JCodeModel codeModel = new JCodeModel();

        URL source = inputJson;

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
            public SourceType getSourceType(){
                return SourceType.JSON;
            }
        };
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, className, packageName, source);

        codeModel.build(outputPojoDirectory);
    }
}
