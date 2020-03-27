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

    public String runBmi(int height, int weight) {

        final String dmn = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
                "<semantic:definitions xmlns:semantic=\"http://www.omg.org/spec/DMN/20180521/MODEL/\"  xmlns:triso=\"http://www.trisotech.com/2015/triso/modeling\"  xmlns:dmndi=\"http://www.omg.org/spec/DMN/20180521/DMNDI/\"  xmlns:di=\"http://www.omg.org/spec/DMN/20180521/DI/\"  xmlns:dc=\"http://www.omg.org/spec/DMN/20180521/DC/\"  xmlns:trisodmn=\"http://www.trisotech.com/2016/triso/dmn\"  xmlns:feel=\"http://www.omg.org/spec/DMN/20180521/FEEL/\"  xmlns:tc=\"http://www.omg.org/spec/DMN/20160719/testcase\"  xmlns:drools=\"http://www.drools.org/kie/dmn/1.1\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xmlns:rss=\"http://purl.org/rss/2.0/\"  xmlns:openapi=\"https://openapis.org/omg/extension/1.0\"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns=\"http://www.trisotech.com/definitions/_90a9fca6-4312-478e-b109-31237bf9c316\" id=\"_90a9fca6-4312-478e-b109-31237bf9c316\" name=\"Test\" namespace=\"http://www.trisotech.com/definitions/_90a9fca6-4312-478e-b109-31237bf9c316\" exporter=\"Decision Modeler\" exporterVersion=\"6.9.3\" triso:logoChoice=\"Default\">\n" +
                "    <semantic:extensionElements/>\n" +
                "    <semantic:itemDefinition name=\"tBMIScore\" label=\"tBMIScore\" isCollection=\"false\">\n" +
                "        <semantic:typeRef>number</semantic:typeRef>\n" +
                "    </semantic:itemDefinition>\n" +
                "    <semantic:decisionService id=\"_90a9fca6-4312-478e-b109-31237bf9c316_DS\" name=\"Whole Model Decision Service\" triso:dynamicDecisionService=\"true\" triso:wholeModelService=\"true\">\n" +
                "        <semantic:variable name=\"Whole Model Decision Service\" id=\"_90a9fca6-4312-478e-b109-31237bf9c316_DS_VAR\" typeRef=\"Any\"/>\n" +
                "        <semantic:outputDecision href=\"#_529abd30-9d90-46ee-bbab-9550a974b702\"/>\n" +
                "        <semantic:inputData href=\"#_f53df237-486a-42fd-95b1-64af7f0ae90b\"/>\n" +
                "        <semantic:inputData href=\"#_d267003d-400e-4026-bb43-5976137c8b6e\"/>\n" +
                "    </semantic:decisionService>\n" +
                "    <semantic:decisionService id=\"_13730806-1922-46dd-935f-3a818dccd822_DS\" name=\"Diagram Page 1\" triso:dynamicDecisionService=\"true\">\n" +
                "        <semantic:variable name=\"Diagram Page 1\" id=\"_13730806-1922-46dd-935f-3a818dccd822_DS_VAR\" typeRef=\"Any\"/>\n" +
                "        <semantic:outputDecision href=\"#_529abd30-9d90-46ee-bbab-9550a974b702\"/>\n" +
                "        <semantic:inputData href=\"#_f53df237-486a-42fd-95b1-64af7f0ae90b\"/>\n" +
                "        <semantic:inputData href=\"#_d267003d-400e-4026-bb43-5976137c8b6e\"/>\n" +
                "    </semantic:decisionService>\n" +
                "    <semantic:inputData id=\"_f53df237-486a-42fd-95b1-64af7f0ae90b\" name=\"Height\">\n" +
                "        <semantic:variable name=\"Height\" id=\"_db7b20b4-c75e-441a-933d-9a1598a78373\" typeRef=\"string\"/>\n" +
                "    </semantic:inputData>\n" +
                "    <semantic:inputData id=\"_d267003d-400e-4026-bb43-5976137c8b6e\" name=\"Weight\">\n" +
                "        <semantic:variable name=\"Weight\" id=\"_c1630fc0-3a03-450c-ab69-19b889df9313\" typeRef=\"string\"/>\n" +
                "    </semantic:inputData>\n" +
                "    <semantic:decision id=\"_529abd30-9d90-46ee-bbab-9550a974b702\" name=\"Find BMI\">\n" +
                "        <semantic:variable name=\"Find BMI\" id=\"_1f45fdae-aa08-4ca5-bbb0-baf36ff2e45b\" typeRef=\"Any\"/>\n" +
                "        <semantic:informationRequirement id=\"_f8be43de-1057-4940-8ea6-4142132448ba\">\n" +
                "            <semantic:requiredInput href=\"#_f53df237-486a-42fd-95b1-64af7f0ae90b\"/>\n" +
                "        </semantic:informationRequirement>\n" +
                "        <semantic:informationRequirement id=\"_1ccdc6ca-2f43-42ff-8189-705c1b49cfce\">\n" +
                "            <semantic:requiredInput href=\"#_d267003d-400e-4026-bb43-5976137c8b6e\"/>\n" +
                "        </semantic:informationRequirement>\n" +
                "        <semantic:knowledgeRequirement id=\"_df6bd981-da6b-4b22-b729-c6249e6d0334\">\n" +
                "            <semantic:requiredKnowledge href=\"#_a58305d2-031a-4d0d-8f31-89a78064d40c\"/>\n" +
                "        </semantic:knowledgeRequirement>\n" +
                "        <semantic:context id=\"_4da07499-1b6b-4ba3-963a-2b4b71ff1925\" typeRef=\"Any\" triso:expressionId=\"_c2e27887-cf42-4a80-9d38-db453135a535\">\n" +
                "            <semantic:contextEntry id=\"_70520a0d-efe9-41f1-95ea-80fbb68f0f70\">\n" +
                "                <semantic:variable name=\"BMI Score\" id=\"_19d52598-3b2d-41f2-b0e9-1ec106949d3c\" typeRef=\"number\"/>\n" +
                "                <semantic:invocation id=\"_52322ac1-9a0b-430d-a08a-75a038566fff\">\n" +
                "                    <semantic:literalExpression id=\"literal__52322ac1-9a0b-430d-a08a-75a038566fff\">\n" +
                "                        <semantic:text>BMI Formula</semantic:text>\n" +
                "                    </semantic:literalExpression>\n" +
                "                    <semantic:binding>\n" +
                "                        <semantic:parameter id=\"_a9ad947f-a665-4aa1-b8f8-0d0d82ae8272\" name=\"H\"/>\n" +
                "                        <semantic:literalExpression id=\"_6783c277-f1ff-4254-8e81-220a47936a25\">\n" +
                "                            <semantic:text>Height</semantic:text>\n" +
                "                        </semantic:literalExpression>\n" +
                "                    </semantic:binding>\n" +
                "                    <semantic:binding>\n" +
                "                        <semantic:parameter id=\"_3ab22521-ea12-4f05-bb4d-9f96700f3bac\" name=\"W\"/>\n" +
                "                        <semantic:literalExpression id=\"_d0316fbd-e041-42cf-a856-4ad2c923fd56\">\n" +
                "                            <semantic:text>Weight  </semantic:text>\n" +
                "                        </semantic:literalExpression>\n" +
                "                    </semantic:binding>\n" +
                "                </semantic:invocation>\n" +
                "            </semantic:contextEntry>\n" +
                "            <semantic:contextEntry id=\"_4081003a-f281-42f0-87e4-b77956f72c6c\">\n" +
                "                <semantic:variable name=\"BMI Category\" id=\"_6acaef95-f94f-4c32-a59c-011469ce772d\" typeRef=\"Any\"/>\n" +
                "                <semantic:decisionTable id=\"_35cb0786-be09-4f52-9ea7-2eb437e8e614\" hitPolicy=\"UNIQUE\" outputLabel=\"BMI Category\">\n" +
                "                    <semantic:input id=\"_ba71c780-22db-4d0d-ae73-32950e7b1479\" triso:allowNull=\"true\" label=\"BMI Score\">\n" +
                "                        <semantic:inputExpression typeRef=\"number\">\n" +
                "                            <semantic:text>BMI Score</semantic:text>\n" +
                "                        </semantic:inputExpression>\n" +
                "                    </semantic:input>\n" +
                "                    <semantic:output id=\"_9fcb6dff-75e9-41be-b665-67771a4f61fd\" triso:allowNull=\"true\"/>\n" +
                "                    <semantic:annotation name=\"Description\"/>\n" +
                "                    <semantic:rule id=\"_7249f998-7622-4e7b-a662-096b4b3d8840\">\n" +
                "                        <semantic:inputEntry id=\"_2f93a4ac-d587-42a0-b985-79469bd78a84\">\n" +
                "                            <semantic:text>&lt;18.5</semantic:text>\n" +
                "                        </semantic:inputEntry>\n" +
                "                        <semantic:outputEntry id=\"_763c1058-8616-49b7-a8bd-8e43bd9c597a\">\n" +
                "                            <semantic:text>\"Under weight\"</semantic:text>\n" +
                "                        </semantic:outputEntry>\n" +
                "                        <semantic:annotationEntry>\n" +
                "                            <semantic:text/>\n" +
                "                        </semantic:annotationEntry>\n" +
                "                    </semantic:rule>\n" +
                "                    <semantic:rule id=\"_62fa3adb-503b-4a30-962a-27c4eae46852\">\n" +
                "                        <semantic:inputEntry id=\"_75c56c37-95af-4362-8735-c23d08e58aec\">\n" +
                "                            <semantic:text>[18.5..25)</semantic:text>\n" +
                "                        </semantic:inputEntry>\n" +
                "                        <semantic:outputEntry id=\"_68806b13-1768-4385-bd70-f9550736b493\">\n" +
                "                            <semantic:text>\"Normal Weight\"</semantic:text>\n" +
                "                        </semantic:outputEntry>\n" +
                "                        <semantic:annotationEntry>\n" +
                "                            <semantic:text/>\n" +
                "                        </semantic:annotationEntry>\n" +
                "                    </semantic:rule>\n" +
                "                    <semantic:rule id=\"_0b872828-5ca3-4008-bff8-941fd9edcc9b\">\n" +
                "                        <semantic:inputEntry id=\"_ba08fdc9-c52e-41cb-b271-1dcba4698d9e\">\n" +
                "                            <semantic:text>[25..30)</semantic:text>\n" +
                "                        </semantic:inputEntry>\n" +
                "                        <semantic:outputEntry id=\"_308efd1f-6b1f-42c3-a999-3d70812816b1\">\n" +
                "                            <semantic:text>\"Over Weight\"</semantic:text>\n" +
                "                        </semantic:outputEntry>\n" +
                "                        <semantic:annotationEntry>\n" +
                "                            <semantic:text/>\n" +
                "                        </semantic:annotationEntry>\n" +
                "                    </semantic:rule>\n" +
                "                    <semantic:rule id=\"_9c08822d-503c-4bc4-adcb-8557d41cc1f8\">\n" +
                "                        <semantic:inputEntry id=\"_e0b0f83d-92df-43dd-bc05-037e00a6fa9c\">\n" +
                "                            <semantic:text>&gt;=30</semantic:text>\n" +
                "                        </semantic:inputEntry>\n" +
                "                        <semantic:outputEntry id=\"_e52fa589-f236-40f7-bd9b-e49977e0d51e\">\n" +
                "                            <semantic:text>\"Obese\"</semantic:text>\n" +
                "                        </semantic:outputEntry>\n" +
                "                        <semantic:annotationEntry>\n" +
                "                            <semantic:text/>\n" +
                "                        </semantic:annotationEntry>\n" +
                "                    </semantic:rule>\n" +
                "                </semantic:decisionTable>\n" +
                "            </semantic:contextEntry>\n" +
                "            <semantic:contextEntry id=\"_70747767-eb9e-403f-b89f-d1e48ec995e1\">\n" +
                "                <semantic:literalExpression id=\"_f77ecf82-5b24-4af2-ab23-a4b4bc7921dc\">\n" +
                "                    <semantic:text>BMI Category</semantic:text>\n" +
                "                </semantic:literalExpression>\n" +
                "            </semantic:contextEntry>\n" +
                "        </semantic:context>\n" +
                "    </semantic:decision>\n" +
                "    <semantic:decisionService id=\"_32e7c4ef-15f4-420a-ba69-ae106c2d1546\" name=\"Decision Service Find BMI\">\n" +
                "        <semantic:variable name=\"Decision Service Find BMI\" id=\"_ea513fec-6ac1-4ecd-9cea-4596a28af5f3\" typeRef=\"Any\"/>\n" +
                "        <semantic:outputDecision href=\"#_529abd30-9d90-46ee-bbab-9550a974b702\"/>\n" +
                "        <semantic:inputData href=\"#_f53df237-486a-42fd-95b1-64af7f0ae90b\"/>\n" +
                "        <semantic:inputData href=\"#_d267003d-400e-4026-bb43-5976137c8b6e\"/>\n" +
                "    </semantic:decisionService>\n" +
                "    <semantic:businessKnowledgeModel id=\"_a58305d2-031a-4d0d-8f31-89a78064d40c\" name=\"BMI Formula\">\n" +
                "        <semantic:variable name=\"BMI Formula\" id=\"_0c8db230-dfc8-45f2-a7c1-0e4f69d30bb8\" typeRef=\"number\"/>\n" +
                "        <semantic:encapsulatedLogic id=\"_347da631-130c-4541-9584-93c7a7fb99dc\" kind=\"FEEL\" typeRef=\"number\" triso:expressionId=\"_a3a35e2e-b9ac-43d1-885b-07ee3895641f\">\n" +
                "            <semantic:formalParameter name=\"H\" typeRef=\"number\" id=\"_f6dd4cd1-90a3-464c-91f8-3ff7f73c161b\"/>\n" +
                "            <semantic:formalParameter name=\"W\" typeRef=\"number\" id=\"_a518ffd3-0489-4a5d-8396-55310bd019fb\"/>\n" +
                "            <semantic:literalExpression id=\"_e6f6843f-817b-41f3-bb1e-17719e25fbde\" expressionLanguage=\"http://www.omg.org/spec/DMN/20180521/FEEL/\" triso:unparsed=\"false\" typeRef=\"number\">\n" +
                "                <semantic:text>decimal((W/H**2)*703,1)</semantic:text>\n" +
                "            </semantic:literalExpression>\n" +
                "        </semantic:encapsulatedLogic>\n" +
                "    </semantic:businessKnowledgeModel>\n" +
                "    <dmndi:DMNDI>\n" +
                "        <dmndi:DMNDiagram id=\"_13730806-1922-46dd-935f-3a818dccd822\" triso:modelElementRef=\"_6ee7ea4b-665d-4b5b-8fd4-793c606ec387\" name=\"Page 1\">\n" +
                "            <di:extension/>\n" +
                "            <dmndi:Size height=\"1050\" width=\"1485\"/>\n" +
                "            <dmndi:DMNShape id=\"_d1f23a62-080e-47eb-a3d5-d94b14b04a2b\" dmnElementRef=\"_f53df237-486a-42fd-95b1-64af7f0ae90b\">\n" +
                "                <dc:Bounds x=\"350.7588291168213\" y=\"392.99999618530273\" width=\"135.48234176635742\" height=\"60.00000762939453\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\" trisodmn:defaultBounds=\"true\"/>\n" +
                "            </dmndi:DMNShape>\n" +
                "            <dmndi:DMNShape id=\"_53ec140c-4f2b-4bf3-ab35-6fae44f8045d\" dmnElementRef=\"_d267003d-400e-4026-bb43-5976137c8b6e\">\n" +
                "                <dc:Bounds x=\"665.7588291168213\" y=\"392.99999618530273\" width=\"135.48234176635742\" height=\"60.00000762939453\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\" trisodmn:defaultBounds=\"true\"/>\n" +
                "            </dmndi:DMNShape>\n" +
                "            <dmndi:DMNShape id=\"_ffc5f150-e9d0-4fb8-bdcf-4d4e30155180\" dmnElementRef=\"_529abd30-9d90-46ee-bbab-9550a974b702\">\n" +
                "                <dc:Bounds x=\"477\" y=\"205\" width=\"153\" height=\"60\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\" trisodmn:defaultBounds=\"true\"/>\n" +
                "            </dmndi:DMNShape>\n" +
                "            <dmndi:DMNShape id=\"_f9347ff9-2f7f-46fe-b70a-827d34ec36a2\" dmnElementRef=\"_a58305d2-031a-4d0d-8f31-89a78064d40c\">\n" +
                "                <dc:Bounds x=\"754\" y=\"205\" width=\"153\" height=\"60\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\" trisodmn:defaultBounds=\"true\"/>\n" +
                "            </dmndi:DMNShape>\n" +
                "            <dmndi:DMNEdge id=\"_fa62bba0-fa95-4ed5-b833-94b3100eae19\" dmnElementRef=\"_df6bd981-da6b-4b22-b729-c6249e6d0334\">\n" +
                "                <di:waypoint x=\"755\" y=\"235\"/>\n" +
                "                <di:waypoint x=\"630\" y=\"235\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\"/>\n" +
                "            </dmndi:DMNEdge>\n" +
                "            <dmndi:DMNEdge id=\"_be9fc277-aebd-4f5a-b3f5-299306a23d40\" dmnElementRef=\"_f8be43de-1057-4940-8ea6-4142132448ba\">\n" +
                "                <di:waypoint x=\"486.2411708831787\" y=\"422.99999618530273\"/>\n" +
                "                <di:waypoint x=\"523.5\" y=\"265\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\"/>\n" +
                "            </dmndi:DMNEdge>\n" +
                "            <dmndi:DMNEdge id=\"_13b53486-bca9-4f7a-b050-795f87beff64\" dmnElementRef=\"_1ccdc6ca-2f43-42ff-8189-705c1b49cfce\">\n" +
                "                <di:waypoint x=\"665.9968013763428\" y=\"422.99999618530273\"/>\n" +
                "                <di:waypoint x=\"583.5\" y=\"265\"/>\n" +
                "                <dmndi:DMNLabel sharedStyle=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\"/>\n" +
                "            </dmndi:DMNEdge>\n" +
                "        </dmndi:DMNDiagram>\n" +
                "        <dmndi:DMNStyle id=\"LS_90a9fca6-4312-478e-b109-31237bf9c316_0\" fontFamily=\"arial,helvetica,sans-serif\" fontSize=\"11\" fontBold=\"false\" fontItalic=\"false\" fontUnderline=\"false\" fontStrikeThrough=\"false\"/>\n" +
                "    </dmndi:DMNDI>\n" +
                "</semantic:definitions>";


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

        dmnContext.set("Height", height);
        dmnContext.set("Weight", weight);

        DMNResult dmnResult = dmnRuntime.evaluateAll(lstdmnModel.get(0), dmnContext);

        String result="";

        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            result=result+"Decision '" + dr.getDecisionName() + "' : " + dr.getResult();
        }

        return result;

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
