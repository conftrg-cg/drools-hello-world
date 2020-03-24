package com.dmn;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;


public class DMNRunner {

    public String run(String season, int guestsCount) {

        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        DMNRuntime dmnRuntime = kieContainer.newKieSession("ksession").getKieRuntime(DMNRuntime.class);
        DMNModel dmnModel = dmnRuntime.getModel("http://www.trisotech.com/definitions/_15587d56-cedf-4cef-9f16-fe20b223021b",
                "dish-decision");

        DMNContext dmnContext = dmnRuntime.newContext();

        dmnContext.set("Season", season);
        dmnContext.set("How many guests", guestsCount);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        StringBuilder stringBuilder = new StringBuilder();

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            stringBuilder.append("Decision '" + dr.getDecisionName() + "' : " + dr.getResult());
        }

        return stringBuilder.toString();
    }

    public String runNew(String season, int guestsCount)
    {
        final String dmn = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<semantic:definitions xmlns:semantic=\"http://www.omg.org/spec/DMN/20151101/dmn.xsd\" xmlns=\"http://www.trisotech.com/definitions/_15587d56-cedf-4cef-9f16-fe20b223021b\" xmlns:feel=\"http://www.omg.org/spec/FEEL/20140401\" xmlns:tc=\"http://www.omg.org/spec/DMN/20160719/testcase\" xmlns:triso=\"http://www.trisotech.com/2015/triso/modeling\" xmlns:trisofeed=\"http://trisotech.com/feed\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" exporter=\"DMN Modeler\" exporterVersion=\"5.2.14.2\" id=\"_15587d56-cedf-4cef-9f16-fe20b223021b\" name=\"dish-decision\" namespace=\"http://www.trisotech.com/definitions/_15587d56-cedf-4cef-9f16-fe20b223021b\" triso:logoChoice=\"Default\">\n" +
                "  <semantic:extensionElements/>\n" +
                "  <semantic:itemDefinition label=\"tSeason\" name=\"tSeason\">\n" +
                "    <semantic:typeRef>feel:string</semantic:typeRef>\n" +
                "    <semantic:allowedValues triso:constraintsType=\"enumeration\">\n" +
                "      <semantic:text>\"Fall\",\"Winter\",\"Spring\",\"Summer\"</semantic:text>\n" +
                "    </semantic:allowedValues>\n" +
                "  </semantic:itemDefinition>\n" +
                "  <semantic:itemDefinition label=\"tGuests\" name=\"tGuests\">\n" +
                "    <semantic:typeRef>feel:number</semantic:typeRef>\n" +
                "  </semantic:itemDefinition>\n" +
                "  <semantic:itemDefinition label=\"tDish\" name=\"tDish\">\n" +
                "    <semantic:typeRef>feel:string</semantic:typeRef>\n" +
                "  </semantic:itemDefinition>\n" +
                "  <semantic:decision id=\"_6aceedc5-4809-4a73-810c-aab05eb94f2d\" name=\"Dish Decision\">\n" +
                "    <semantic:variable id=\"_899da7ed-04eb-4b5e-aeb1-5f1119b23484\" name=\"Dish Decision\" typeRef=\"tDish\"/>\n" +
                "    <semantic:informationRequirement>\n" +
                "      <semantic:requiredInput href=\"#_d2ea8047-0a34-4942-91ff-e718ce5880c6\"/>\n" +
                "    </semantic:informationRequirement>\n" +
                "    <semantic:informationRequirement>\n" +
                "      <semantic:requiredInput href=\"#_9d7010c2-4ec8-4ff7-998e-9bb65fb117a7\"/>\n" +
                "    </semantic:informationRequirement>\n" +
                "    <semantic:decisionTable hitPolicy=\"UNIQUE\" id=\"_90997060-896e-49a6-9ad4-b1bcc46a5846\" outputLabel=\"Dish Decision\">\n" +
                "      <semantic:input id=\"_15f27d6b-9a5a-427d-baf6-a2163a0bf43c\">\n" +
                "        <semantic:inputExpression typeRef=\"tSeason\">\n" +
                "          <semantic:text>Season</semantic:text>\n" +
                "        </semantic:inputExpression>\n" +
                "        <semantic:inputValues triso:constraintsType=\"enumeration\">\n" +
                "          <semantic:text>\"Fall\",\"Winter\",\"Spring\",\"Summer\"</semantic:text>\n" +
                "        </semantic:inputValues>\n" +
                "      </semantic:input>\n" +
                "      <semantic:input id=\"_5527901a-1404-44a9-a50c-72b65eeffd00\">\n" +
                "        <semantic:inputExpression typeRef=\"tGuests\">\n" +
                "          <semantic:text>How many guests</semantic:text>\n" +
                "        </semantic:inputExpression>\n" +
                "      </semantic:input>\n" +
                "      <semantic:output id=\"_634f46b6-bd3f-41e6-9956-2baea0c34f70\"/>\n" +
                "      <semantic:rule id=\"_5622957e-bc0c-492b-9ce6-2fe6405eeb20\">\n" +
                "        <semantic:inputEntry id=\"_57837258-4782-4674-ad8d-4bfb14d81317\">\n" +
                "          <semantic:text>\"Fall\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_1d236568-bfa0-4dfb-8fcb-8062e279b4b7\">\n" +
                "          <semantic:text>&lt;= 8</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_379a2c77-b8f1-482b-b073-14e6f9130670\">\n" +
                "          <semantic:text>\"Spareribs\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "      <semantic:rule id=\"_a0e212d9-d632-4a39-bc28-db009afef98a\">\n" +
                "        <semantic:inputEntry id=\"_b599157e-0c41-42ce-94af-abd655706169\">\n" +
                "          <semantic:text>\"Winter\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_8beed46d-770e-4df7-bfd6-131fc3dd0aba\">\n" +
                "          <semantic:text>&lt;= 8</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_96c195cc-bd29-414c-95ec-2d00d46e037f\">\n" +
                "          <semantic:text>\"Roastbeef\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "      <semantic:rule id=\"_5c48feca-ef56-437a-9bfe-cc3008e456b6\">\n" +
                "        <semantic:inputEntry id=\"_25d6b3c0-dad8-4798-ba6b-aabaf1798ca2\">\n" +
                "          <semantic:text>\"Spring\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_14848008-db78-4405-b145-04b07f762a87\">\n" +
                "          <semantic:text>&lt;= 4</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_3e174a64-f5c9-448e-a52f-324a08ca5c23\">\n" +
                "          <semantic:text>\"Dry Aged Gourmet Steak\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "      <semantic:rule id=\"_ccdcf5d1-75a6-447b-8285-eeaad8440945\">\n" +
                "        <semantic:description>Save money</semantic:description>\n" +
                "        <semantic:inputEntry id=\"_96c60ba8-b761-476e-99f9-cbb02657f47b\">\n" +
                "          <semantic:text>\"Spring\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_c1f1c2e7-1d76-48bb-a9f7-f53739d77a57\">\n" +
                "          <semantic:text>[5..8]</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_6f3883a9-113f-4e16-96dd-361f2e8c7917\">\n" +
                "          <semantic:text>\"Steak\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "      <semantic:rule id=\"_a73cdf14-0d81-4e2f-bbc7-ed8b92cc5c4d\">\n" +
                "        <semantic:description>Less effort</semantic:description>\n" +
                "        <semantic:inputEntry id=\"_b4ef3b99-e442-407c-a201-fb06e51c562d\">\n" +
                "          <semantic:text>\"Fall\", \"Winter\", \"Spring\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_5a417624-b9ac-4ea8-acf0-ceafaa52250b\">\n" +
                "          <semantic:text>&gt; 8</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_fb97fd8c-798b-4a98-80c7-5853d8151127\">\n" +
                "          <semantic:text>\"Stew\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "      <semantic:rule id=\"_d7e6f6f5-b640-42f3-88a5-886b03ffc34c\">\n" +
                "        <semantic:description>Hey, why not!?</semantic:description>\n" +
                "        <semantic:inputEntry id=\"_9a97da0a-37ef-44a3-a891-7d2e0ed88f1a\">\n" +
                "          <semantic:text>\"Summer\"</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:inputEntry id=\"_38efbdb0-debc-4dc3-8ef0-4c973e1c6124\">\n" +
                "          <semantic:text>-</semantic:text>\n" +
                "        </semantic:inputEntry>\n" +
                "        <semantic:outputEntry id=\"_c45c1448-1ac5-464b-afd4-e896a50376c7\">\n" +
                "          <semantic:text>\"Light Salad and a nice Steak\"</semantic:text>\n" +
                "        </semantic:outputEntry>\n" +
                "      </semantic:rule>\n" +
                "    </semantic:decisionTable>\n" +
                "  </semantic:decision>\n" +
                "  <semantic:inputData id=\"_d2ea8047-0a34-4942-91ff-e718ce5880c6\" name=\"Season\">\n" +
                "    <semantic:variable id=\"_14c791d2-1029-444b-944e-3c12e3995b56\" name=\"Season\" typeRef=\"tSeason\"/>\n" +
                "  </semantic:inputData>\n" +
                "  <semantic:inputData id=\"_9d7010c2-4ec8-4ff7-998e-9bb65fb117a7\" name=\"How many guests\">\n" +
                "    <semantic:variable id=\"_269694a2-22bc-4c17-a1be-82f488cdff16\" name=\"How many guests\" typeRef=\"tGuests\"/>\n" +
                "  </semantic:inputData>\n" +
                "</semantic:definitions>\n" +
                "";


        final String KBASE_NAME = "kieBase";
        final String KSESSION_NAME = "kieSession";
        final KieServices ks = KieServices.Factory.get();
        final KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write( "src/main/resources/test.dmn", dmn );

        final KieModuleModel kmoduleModel = ks.newKieModuleModel();
        kmoduleModel.newKieBaseModel( KBASE_NAME )
                .addPackage( "*" )
                .newKieSessionModel( KSESSION_NAME )
                .setDefault( true );

        kfs.writeKModuleXML( kmoduleModel.toXML() );

        final KieBuilder builder = ks.newKieBuilder( kfs ).buildAll();

        ks.getRepository().addKieModule( builder.getKieModule() );


        KieContainer kieContainer = ks.newKieContainer( ks.getRepository().getDefaultReleaseId() );
        DMNRuntime dmnRuntime = kieContainer.newKieSession().getKieRuntime( DMNRuntime.class );
        //DMNModel dmnModel = dmnRuntime.getModel("http://www.trisotech.com/definitions/_15587d56-cedf-4cef-9f16-fe20b223021b", "test");
        List<DMNModel> lstdmnModel =dmnRuntime.getModels();

        DMNContext dmnContext = dmnRuntime.newContext();

        dmnContext.set("Season", season);
        dmnContext.set("How many guests", guestsCount);

        DMNResult dmnResult = dmnRuntime.evaluateAll(lstdmnModel.get(0), dmnContext);

        String result="";

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Decision '" + dr.getDecisionName() + "' : " + dr.getResult());

            result=result+"Decision '" + dr.getDecisionName() + "' : " + dr.getResult();
        }

        return result;
    }

}
