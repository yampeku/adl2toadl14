package es.veratech.adl2converter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
//import org.ehr.am.constraintmodel.ArchetypeInternalRef;
//import org.ehr.am.constraintmodel.CMultipleAttribute;
//import org.ehr.am.constraintmodel.CSingleAttribute;
//import org.ehr.am.constraintmodel.assertion.Assertion;
//import org.ehr.am.constraintmodel.assertion.ExpressionItem;
//import org.ehr.rm.openehr.datatypes.quantity.datetime.DvDate;
//import org.ehr.rm.openehr.datatypes.quantity.datetime.DvDateTime;
//import org.ehr.rm.openehr.datatypes.quantity.datetime.DvDuration;
//import org.ehr.rm.openehr.datatypes.quantity.datetime.DvTime;
//import org.ehr.rm.openehr.datatypes.text.CodePhrase;
//import org.ehr.rm.openehr.support.basic.Interval;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CAttribute.Existence;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.serialize.ADLSerializer;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;
import org.openehr.referencemodels.BuiltinReferenceModels;

import com.nedap.archie.ArchieLanguageConfiguration;
import com.nedap.archie.adlparser.ADLParseException;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.ArchetypeSlot;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.CComplexObjectProxy;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.aom.ResourceDescription;
import com.nedap.archie.aom.ResourceDescriptionItem;
import com.nedap.archie.aom.primitives.CBoolean;
import com.nedap.archie.aom.primitives.CDate;
import com.nedap.archie.aom.primitives.CDateTime;
import com.nedap.archie.aom.primitives.CDuration;
import com.nedap.archie.aom.primitives.CInteger;
import com.nedap.archie.aom.primitives.CReal;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.primitives.CTime;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.base.Cardinality;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.MetaModels;

//import es.veratech.linkehr.studio.io.IO;
//import es.veratech.linkehr.studio.io.IO.Format;
//import es.veratech.linkehr.studio.semantic.ArchetypeBuilder;

/**
 * Hello world!
 *
 */
public class Adl2import 
{
    private MetaModels mm;
    private Archetype arch2;
    org.openehr.am.archetype.Archetype arch14;
    
	public static void main( String[] args )
    {
		Adl2import adl2import = new Adl2import();
		adl2import.setMetaModels(BuiltinReferenceModels.getMetaModels());
    	ArchieRMInfoLookup lookup = ArchieRMInfoLookup.getInstance();
        //String path="c:/temp/arch/openEHR-EHR-EVALUATION.mixed_aom_node_types.v1.0.0.adls";
    	String path="c:/temp/arch/openehr-TEST_PKG-WHOLE.primitive_types.v1.0.0.adls";
    	Archetype arch;
    	try {
    		arch = adl2import.parseArch(new File(path));
    	} catch (ADLParseException e) {
    		e.printStackTrace();
    		return;
    	}
    	adl2import.setArch2(arch);
    	ArchieLanguageConfiguration.setDefaultMeaningAndDescriptionLanguage("en");
    	org.openehr.am.archetype.Archetype arch14;
    	try {
    		arch14 = adl2import.createEmptyArchetype(arch.getArchetypeId().getRmPublisher(), arch.getArchetypeId().getRmPackage(), arch.getArchetypeId().getRmClass(),  arch.getArchetypeId().getConceptId(), arch.getOriginalLanguage().getCodeString(),arch);
    		adl2import.setArch14(arch14);
    		System.out.println(adl2import.serializeArchetype(arch14));
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	//adl2import.processDefinition(arch.getDefinition(),arch14.getDefinition());

    	//adl2import.transformMeta(arch.getDescription(),arch14.getDescription());


//        try {
//			System.out.println(adl2import.serializeArchetype(arch14));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    
    
    private void setArch2(Archetype arch) {
		arch2=arch;
		
	}



	private void setArch14(org.openehr.am.archetype.Archetype arch) {
		arch14=arch;
		
	}



	private void setMetaModels(MetaModels metaModels) {
		mm=metaModels;
		
	}



	private String serializeArchetype(org.openehr.am.archetype.Archetype arch14) {
		ADLSerializer adlserializer=new ADLSerializer();
		
		try {
			return adlserializer.output(arch14);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



//	private void processDefinition(CComplexObject definition,
//			org.openehr.am.archetype.constraintmodel.CComplexObject definition14) {
//    	
//    	String nodeid14 = idcode2atcode(definition.getNodeId());
//    	definition14.setNodeId(nodeid14);
//    	//definition14.getArchetype().getArchetypeId().ConceptName(nodeid14);
//    	ArchetypeTerm term = definition.getTerm();
//    	addTermDefinition(arch14.getOntology(),nodeid14, term.getText(), term.getDescription());
//    	//arch14.getOntology().addTermDefinition(nodeid14, term.getText(), term.getDescription());
//    	
//    	transformRecursive(definition,definition14);
//		
//	}

	private org.openehr.am.archetype.constraintmodel.CComplexObject createDefinition(CComplexObject definition, ArchetypeOntology ontology) {
    	
		org.openehr.am.archetype.constraintmodel.CComplexObject definition14 = new org.openehr.am.archetype.constraintmodel.CComplexObject("/",definition.getRmTypeName(),new Interval<Integer>(1, 1),idcode2atcode(definition.getNodeId()),new ArrayList<org.openehr.am.archetype.constraintmodel.CAttribute>(),null);		
    	String nodeid14 = idcode2atcode(definition.getNodeId());
    	definition14.setNodeId(nodeid14);
    	//definition14.getArchetype().getArchetypeId().ConceptName(nodeid14);
    	ArchetypeTerm term = definition.getTerm();
    	addTermDefinition(ontology,nodeid14, term.getText(), term.getDescription());
    	//arch14.getOntology().addTermDefinition(nodeid14, term.getText(), term.getDescription());
    	
    	transformRecursive(definition,definition14);
    	
    	return definition14;
		
	}

	private void addTermDefinition(ArchetypeOntology ontology, String nodeid14, String text, String description) {
		for(OntologyDefinitions od:ontology.getTermDefinitionsList())
		{
			if(od.getLanguage().equals(ontology.getPrimaryLanguage()))
			{
				od.getDefinitions().add(new org.openehr.am.archetype.ontology.ArchetypeTerm(nodeid14, text, description));
			}
		}
		
	}


//
//	private void transformMeta(ResourceDescription description,
//			org.openehr.rm.common.resource.ResourceDescription description14) {
//		
//		
//		Map<String, String> oa = description.getOriginalAuthor();
//		
//		description14.getOriginalAuthor().clear();
//		for(Entry<String, String> entry:oa.entrySet())
//		{
//			description14.getOriginalAuthor().put(entry.getKey(),entry.getValue());
//		}
//		
//		description14.setLifecycleState(description.getLifecycleState().getCodeString());
//		
//		description14.setOtherDetails(new HashMap<String,String>());
//		description14.getOtherDetails().put("copyright", description.getCopyright());
//		
//		for(Entry<String, String> entry:description.getOtherDetails().entrySet()){
//			description14.getOtherDetails().put(entry.getKey(),entry.getValue());
//		}
//		
//		for(Entry<String, ResourceDescriptionItem> entry:description.getDetails().entrySet()){
//			String lang=entry.getKey();
//			ResourceDescriptionItem rdi = entry.getValue();
//
//			HashMap<String, String> originalresourceuri = null;
//			if(rdi.getOriginalResourceUri()!=null)
//			{
//				originalresourceuri=new HashMap<String,String>();
//				for(Entry<String, URI> entryoriginal:rdi.getOriginalResourceUri().entrySet())
//				{
//					originalresourceuri.put(entryoriginal.getKey(), entryoriginal.getValue().toString());
//				}
//			}
//			HashMap<String, String> hashMapOtherdetails = null;
//			if(rdi.getOtherDetails()!=null)
//			{
//				hashMapOtherdetails=new HashMap<String,String>();
//				for(Entry<String, String> entryother:rdi.getOtherDetails().entrySet())
//				{
//					originalresourceuri.put(entryother.getKey(), entryother.getValue());
//				}
//			}
//			org.openehr.rm.common.resource.ResourceDescriptionItem rdi14=new org.openehr.rm.common.resource.ResourceDescriptionItem(new CodePhrase("ISO_639-1", lang),rdi.getPurpose(),new ArrayList<String>(rdi.getKeywords()),rdi.getUse(),rdi.getMisuse(),rdi.getCopyright(),originalresourceuri,hashMapOtherdetails,null);
//			description14.getDetails().add(rdi14);
//		}
//		ResourceDescription rd14=new org.openehr.rm.common.resource.ResourceDescription(rd);
//				
//		
//	}
	
	private org.openehr.rm.common.resource.ResourceDescription createMeta(ResourceDescription description) throws Exception {
		
		
		Map<String, String> oa = description.getOriginalAuthor();
		
		//description14.getOriginalAuthor().clear();
//		for(Entry<String, String> entry:oa.entrySet())
//		{
//			description14.getOriginalAuthor().put(entry.getKey(),entry.getValue());
//		}
		
		//description14.setLifecycleState(description.getLifecycleState().getCodeString());
		
		//description14.setOtherDetails(new HashMap<String,String>());
		//description14.getOtherDetails().put("copyright", description.getCopyright());
		
		HashMap<String, String> otherDetails = new HashMap<String,String>();
		otherDetails.put("copyright", description.getCopyright());
		
		for(Entry<String, String> entry:description.getOtherDetails().entrySet()){
			otherDetails.put(entry.getKey(),entry.getValue());
		}
		HashMap<String, org.openehr.rm.common.resource.ResourceDescriptionItem> details = new HashMap<String,org.openehr.rm.common.resource.ResourceDescriptionItem>();
		
		for(Entry<String, ResourceDescriptionItem> entry:description.getDetails().entrySet()){
			String lang=entry.getKey();
			ResourceDescriptionItem rdi = entry.getValue();

			HashMap<String, String> originalresourceuri = null;
			if(rdi.getOriginalResourceUri()!=null)
			{
				originalresourceuri=new HashMap<String,String>();
				for(Entry<String, URI> entryoriginal:rdi.getOriginalResourceUri().entrySet())
				{
					originalresourceuri.put(entryoriginal.getKey(), entryoriginal.getValue().toString());
				}
			}
			HashMap<String, String> hashMapOtherdetails = null;
			if(rdi.getOtherDetails()!=null)
			{
				hashMapOtherdetails=new HashMap<String,String>();
				for(Entry<String, String> entryother:rdi.getOtherDetails().entrySet())
				{
					originalresourceuri.put(entryother.getKey(), entryother.getValue());
				}
			}
			org.openehr.rm.common.resource.ResourceDescriptionItem rdi14=new org.openehr.rm.common.resource.ResourceDescriptionItem(new CodePhrase("ISO_639-1", lang),rdi.getPurpose(),new ArrayList<String>(rdi.getKeywords()),rdi.getUse(),rdi.getMisuse(),rdi.getCopyright(),originalresourceuri,hashMapOtherdetails,SimpleTerminologyService.getInstance());
			details.put(lang,rdi14);
		}
		org.openehr.rm.common.resource.ResourceDescription rd14=new org.openehr.rm.common.resource.ResourceDescription(new HashMap<String,String>(oa),description.getOtherContributors(),description.getLifecycleState().getCodeString(),details,description.getResourcePackageUri(),otherDetails,null/*parentResource?*/);
				
		return rd14;
	}



	public void transformRecursive(CComplexObject cco, org.openehr.am.archetype.constraintmodel.CComplexObject cco14) {
		cco14.setNodeId(idcode2atcode(cco.getNodeId()));
    	for(CAttribute catt:cco.getAttributes())
    	{
    		org.openehr.am.archetype.constraintmodel.CAttribute catt14;
    		Existence existence;
			if(catt.getExistence()==null || catt.getExistence().isOptional())
				existence=Existence.OPTIONAL;
			else if(catt.getExistence().isMandatory())
				existence=Existence.REQUIRED;
			else
				existence=Existence.NOT_ALLOWED;
    		if(catt.isMultiple())
    		{
    			Cardinality card = catt.getCardinality();
    			Interval<Integer> cardinterval = null;
    			if(card.getInterval()!=null)
    			{
    				cardinterval=new Interval<Integer>(card.getInterval().getLower(), card.getInterval().getUpper());
    			}
    			
				catt14 = new CMultipleAttribute(idpath2atpath(catt.getPath()), catt.getRmAttributeName(), existence,new org.openehr.am.archetype.constraintmodel.Cardinality(card.isOrdered(),card.isUnique(), cardinterval),new ArrayList<org.openehr.am.archetype.constraintmodel.CObject>());
    			
				//((CMultipleAttribute)catt14).setCardinality(new org.openehr.am.archetype.constraintmodel.Cardinality(card.isOrdered(),card.isUnique(), cardinterval));
    		}
    		else
    		{
    			catt14 = new CSingleAttribute(idpath2atpath(catt.getPath()), catt.getRmAttributeName(),existence);
    			
    		}
    		cco14.addAttribute(catt14);
    		
    		for(CObject cobj:catt.getChildren())
    		{
    			String nodeid14 = idcode2atcode(cobj.getNodeId());
    			
    			MultiplicityInterval effectiveOccurrences = cobj.effectiveOccurrences(mm.getSelectedModel()::referenceModelPropMultiplicity);
    			Interval<Integer> occ=new Interval<Integer>(effectiveOccurrences.isLowerUnbounded()?null:effectiveOccurrences.getLower(), effectiveOccurrences.isUpperUnbounded()?null:effectiveOccurrences.getUpper(), effectiveOccurrences.isLowerUnbounded(), effectiveOccurrences.isUpperUnbounded());
    			if(cobj instanceof ArchetypeSlot)
    			{
    				Set<Assertion> includes=getIncludes((ArchetypeSlot)cobj);
					org.openehr.am.archetype.constraintmodel.ArchetypeSlot slot = new org.openehr.am.archetype.constraintmodel.ArchetypeSlot(nodeid14, cobj.getRmTypeName(), occ, nodeid14,catt14, includes, /*excludes*/null);
    				//catt14.addChild(slot);
    				ArchetypeTerm term = cobj.getTerm();
    				addTermDefinition(arch14.getOntology(),nodeid14, term.getText(), term.getDescription());
    				//catt14.getArchetype().getOntology().addTermDefinition(nodeid14, term.getText(), term.getDescription());
    			}
    			else if(cobj instanceof CComplexObject)
    			{

    				CComplexObject childcco = (CComplexObject)cobj;
    				org.openehr.am.archetype.constraintmodel.CComplexObject childcco14=new org.openehr.am.archetype.constraintmodel.CComplexObject(idpath2atpath(cobj.getPath()), cobj.getRmTypeName(),occ,nodeid14,new ArrayList<org.openehr.am.archetype.constraintmodel.CAttribute>(),catt14);
//    				mm.getSelectedModel().referenceModelPropMultiplicity(rmTypeName, rmAttributeNameOrPath)
    				
					childcco14.setOccurrences(new Interval<Integer>(effectiveOccurrences.isLowerUnbounded()?null:effectiveOccurrences.getLower(), effectiveOccurrences.isUpperUnbounded()?null:effectiveOccurrences.getUpper(), effectiveOccurrences.isLowerUnbounded(), effectiveOccurrences.isUpperUnbounded()));
    				
					childcco14.setNodeId(nodeid14);
    				catt14.addChild(childcco14);
    				ArchetypeTerm term = childcco.getTerm();
    				if(term!=null)
    				{
    					addTermDefinition(arch14.getOntology(),nodeid14, term.getText(), term.getDescription());
    					//catt14.getArchetype().getOntology().addTermDefinition(nodeid14, term.getText(), term.getDescription());
    				}
    				else
    				{
    					addTermDefinition(arch14.getOntology(),nodeid14, "", "");
    					//catt14.getArchetype().getOntology().addTermDefinition(nodeid14, "", "");
    				}
    				transformRecursive(childcco,childcco14);
    				
    			}
    			else if(cobj instanceof CComplexObjectProxy)
    			{
    				CComplexObjectProxy ref=(CComplexObjectProxy) cobj;
    				String targetpath=ref.getTargetPath();
    				
					ArchetypeInternalRef internalref = new ArchetypeInternalRef(idpath2atpath(catt.getPath()), cobj.getRmTypeName(),occ,nodeid14,catt14,idpath2atpath(targetpath));
    				internalref.setOccurrences(new Interval<Integer>(effectiveOccurrences.isLowerUnbounded()?null:effectiveOccurrences.getLower(), effectiveOccurrences.isUpperUnbounded()?null:effectiveOccurrences.getUpper(), effectiveOccurrences.isLowerUnbounded(), effectiveOccurrences.isUpperUnbounded()));
    				catt14.addChild(internalref);
    				ArchetypeTerm term = cobj.getTerm();
    				addTermDefinition(arch14.getOntology(),nodeid14, term.getText(), term.getDescription());
    				//catt14.getArchetype().getOntology().addTermDefinition(nodeid14, term.getText(), term.getDescription());
    				
    			}    			
    			else if(cobj instanceof CPrimitiveObject<?, ?>)
    			{
    				org.openehr.am.archetype.constraintmodel.CPrimitiveObject cpo14=transformPrimitiveObject(((CPrimitiveObject<?, ?>)cobj),catt14);
    			}
    		}
    		
    	}
		
//		System.out.println(cComplexObject2.getNodeId()+"  --  "+idcode2atcode(cComplexObject2.getNodeId()));
//        for(CAttribute attribute:cComplexObject2.getAttributes()) {
//            for(CObject child:attribute.getChildren()) {
//            	
//                walk(child);
//            }
//        }
    }
    
    
    private static Set<Assertion> getIncludes(ArchetypeSlot slot) {
    	HashSet<Assertion> assset = new HashSet<Assertion>();
//		for(com.nedap.archie.rules.Assertion include:slot.getIncludes())
//		{
//			assset.add(new Assertion(new ExpressionItem(include.getStringExpression()),include.getStringExpression()));
//		}
		return assset;
	}



	private org.openehr.am.archetype.constraintmodel.CPrimitiveObject transformPrimitiveObject(
			CPrimitiveObject<?, ?> cPrimitiveObject, org.openehr.am.archetype.constraintmodel.CAttribute catt14) {
		org.openehr.am.archetype.constraintmodel.CPrimitiveObject cpresult=null;
		
		
		String nodeid14 = idcode2atcode(cPrimitiveObject.getNodeId());
		MultiplicityInterval effectiveOccurrences = cPrimitiveObject.effectiveOccurrences(mm.getSelectedModel()::referenceModelPropMultiplicity);
		Interval<Integer> occ=new Interval<Integer>(effectiveOccurrences.isLowerUnbounded()?null:effectiveOccurrences.getLower(), effectiveOccurrences.isUpperUnbounded()?null:effectiveOccurrences.getUpper(), effectiveOccurrences.isLowerUnbounded(), effectiveOccurrences.isUpperUnbounded());
		
		
		if(cPrimitiveObject instanceof CBoolean)
		{
			CBoolean cprimitivebool = (CBoolean)cPrimitiveObject;
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14,new org.openehr.am.archetype.constraintmodel.primitive.CBoolean(cprimitivebool.getConstraint().contains(Boolean.TRUE), cprimitivebool.getConstraint().contains(Boolean.FALSE)));
		}
		else if(cPrimitiveObject instanceof CDate)
		{
			CDate cprimitivedate = (CDate)cPrimitiveObject;
			
			List<com.nedap.archie.base.Interval<Temporal>> constraintdate = cprimitivedate.getConstraint();
			
			Interval<DvDate> intervaldvdate=null;
			List<DvDate> listdvdate = null;
			String pattern=cprimitivedate.getPatternedConstraint();
			
			if(pattern==null && cprimitivedate.getConstraint().size()==1 && cprimitivedate.getConstraint().get(0).getLower()!=cprimitivedate.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<Temporal> originalinterval = cprimitivedate.getConstraint().get(0);
				intervaldvdate=new Interval<DvDate>(new DvDate(originalinterval.getLower().toString()), new DvDate(originalinterval.getUpper().toString()), originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else if(pattern==null)
			{
				com.nedap.archie.base.Interval<Temporal> originalinterval = cprimitivedate.getConstraint().get(0);
				listdvdate=new ArrayList<DvDate>();
				listdvdate.add(new DvDate(originalinterval.getLower().toString()));
			}
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(), occ, nodeid14,catt14,new org.openehr.am.archetype.constraintmodel.primitive.CDate(pattern, intervaldvdate,listdvdate));
		}
		else if(cPrimitiveObject instanceof CDateTime)
		{
			CDateTime cprimitivedatetime = (CDateTime)cPrimitiveObject;
			
			List<com.nedap.archie.base.Interval<TemporalAccessor>> constraintdate = cprimitivedatetime.getConstraint();
			
			Interval<DvDateTime> intervaldvdate=null;
			List<DvDateTime> listdvdate = null;
			String pattern=cprimitivedatetime.getPatternedConstraint();
			
			if(pattern==null && cprimitivedatetime.getConstraint().size()==1 && cprimitivedatetime.getConstraint().get(0).getLower()!=cprimitivedatetime.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<TemporalAccessor> originalinterval = cprimitivedatetime.getConstraint().get(0);
				intervaldvdate=new Interval<DvDateTime>(new DvDateTime(originalinterval.getLower().toString()), new DvDateTime(originalinterval.getUpper().toString()), originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else if(pattern==null)
			{
				com.nedap.archie.base.Interval<TemporalAccessor> originalinterval = cprimitivedatetime.getConstraint().get(0);
				listdvdate=new ArrayList<DvDateTime>();
				listdvdate.add(new DvDateTime(originalinterval.getLower().toString()));
			}
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CDateTime(pattern, intervaldvdate,listdvdate));
		}
		else if(cPrimitiveObject instanceof CDuration)
		{
			CDuration cprimitiveduration = (CDuration)cPrimitiveObject;
			List<com.nedap.archie.base.Interval<TemporalAmount>> constraintduration = cprimitiveduration.getConstraint();
			
			DvDuration duration = null;
			Interval<DvDuration> intervaldvdate = null;
			
			DvDuration assumed = null;
			
			String pattern=cprimitiveduration.getPatternedConstraint();
			
			
			if(cprimitiveduration.getConstraint().size()==1 && cprimitiveduration.getConstraint().get(0).getLower()!=cprimitiveduration.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<TemporalAmount> originalinterval = cprimitiveduration.getConstraint().get(0);
				intervaldvdate=new Interval<DvDuration>(originalinterval.getLower()!=null?new DvDuration(originalinterval.getLower().toString()):null, originalinterval.getUpper()!=null?new DvDuration(originalinterval.getUpper().toString()):null, originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else if(pattern==null && cprimitiveduration.getConstraint().size()==1)
			{
				com.nedap.archie.base.Interval<TemporalAmount> originalinterval = cprimitiveduration.getConstraint().get(0);
				duration=new DvDuration(originalinterval.getLower().toString());
			}
			else if(pattern!=null && cprimitiveduration.getConstraint().size()==1)
			{
				com.nedap.archie.base.Interval<TemporalAmount> originalinterval = cprimitiveduration.getConstraint().get(0);
				intervaldvdate=new Interval<DvDuration>(originalinterval.getLower()!=null?new DvDuration(originalinterval.getLower().toString()):null, originalinterval.getUpper()!=null?new DvDuration(originalinterval.getUpper().toString()):null, originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			
			if(cprimitiveduration.getAssumedValue()!=null)
				assumed=new DvDuration(cprimitiveduration.getAssumedValue().toString());
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CDuration(duration, intervaldvdate,assumed,pattern));
		}
		else if(cPrimitiveObject instanceof CInteger)
		{
			CInteger cprimitiveint = (CInteger)cPrimitiveObject;
		
			Interval<Integer> intervalint=null;
			List<Integer> listint = null;
			if(cprimitiveint.getConstraint().size()==1 && cprimitiveint.getConstraint().get(0).getLower()!=cprimitiveint.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<Long> originalinterval = cprimitiveint.getConstraint().get(0);
				intervalint=new Interval<Integer>(originalinterval.getLower()==null?null:originalinterval.getLower().intValue(), originalinterval.getUpper()==null?null:originalinterval.getUpper().intValue(), originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else
			{
				listint=new ArrayList<>();
				for(com.nedap.archie.base.Interval<Long> interval:cprimitiveint.getConstraint()){
					listint.add(interval.getLower().intValue());
				}
			}
			
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CInteger(intervalint,listint));
		}
		else if(cPrimitiveObject instanceof CReal)
		{
			CReal cprimitivereal = (CReal)cPrimitiveObject;
			
			Interval<Double> intervalreal=null;
			List<Double> listreal = null;
			if(cprimitivereal.getConstraint().size()==1 && cprimitivereal.getConstraint().get(0).getLower()!=cprimitivereal.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<Double> originalinterval = cprimitivereal.getConstraint().get(0);
				intervalreal=new Interval<Double>(originalinterval.getLower(), originalinterval.getUpper(), originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else
			{
				listreal=new ArrayList<>();
				for(com.nedap.archie.base.Interval<Double> interval:cprimitivereal.getConstraint()){
					listreal.add(interval.getLower());
				}
			}
			
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CReal(intervalreal,listreal));
		}
		else if(cPrimitiveObject instanceof CString)
		{
			CString cprimitivestr = (CString)cPrimitiveObject;
			String pattern=null;
			ArrayList<String> liststr=null;
			if(cprimitivestr.getConstraint().size()==1 && cprimitivestr.getConstraint().get(0).startsWith("/") && cprimitivestr.getConstraint().get(0).endsWith("/"))
			{
				String constraint = cprimitivestr.getConstraint().get(0);
				pattern=constraint.substring(1, constraint.length()-1);
			}
			else
			{
				liststr=new ArrayList<>(cprimitivestr.getConstraint());
			}
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CString(pattern,liststr));
		}
		else if(cPrimitiveObject instanceof CTerminologyCode)
		{
			
		}
		else if(cPrimitiveObject instanceof CTime)
		{
			CTime cprimitivetime = (CTime)cPrimitiveObject;
			
			List<com.nedap.archie.base.Interval<TemporalAccessor>> constraintdate = cprimitivetime.getConstraint();
			
			Interval<DvTime> intervaldvdate=null;
			List<DvTime> listdvdate = null;
			String pattern=cprimitivetime.getPatternedConstraint();
			
			if(pattern==null && cprimitivetime.getConstraint().size()==1 && cprimitivetime.getConstraint().get(0).getLower()!=cprimitivetime.getConstraint().get(0).getUpper())
			{
				com.nedap.archie.base.Interval<TemporalAccessor> originalinterval = cprimitivetime.getConstraint().get(0);
				intervaldvdate=new Interval<DvTime>(new DvTime(originalinterval.getLower().toString()), new DvTime(originalinterval.getUpper().toString()), originalinterval.isLowerIncluded(), originalinterval.isUpperIncluded());
			}
			else if(pattern==null)
			{
				com.nedap.archie.base.Interval<TemporalAccessor> originalinterval = cprimitivetime.getConstraint().get(0);
				listdvdate=new ArrayList<DvTime>();
				listdvdate.add(new DvTime(originalinterval.getLower().toString()));
			}
			
			cpresult=new org.openehr.am.archetype.constraintmodel.CPrimitiveObject(catt14.path(),occ, nodeid14,catt14, new org.openehr.am.archetype.constraintmodel.primitive.CTime(pattern, intervaldvdate,listdvdate));
		}
		catt14.addChild(cpresult);
		return cpresult;
	}



	public Archetype parseArch(File adlFile) throws ADLParseException{
    	ADLParser parser = new ADLParser();
    	try {
			Archetype archetype = parser.parse(FileUtils.readFileToString(adlFile,"UTF-8"));
			mm.selectModel(archetype);
			return archetype;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public org.openehr.am.archetype.Archetype createEmptyArchetype(String org, String rm, String entity, String concept, String lang, Archetype sourceArch) throws Exception{
    	//return ArchetypeBuilder.getNewArchetype(org+"-"+rm+"-"+entity, concept, "v0", new CodePhrase(lang, "ISO_639-1"));
    	
    	
    	CodePhrase originallanguage=new CodePhrase("ISO_639-1",lang);
		ArchetypeOntology ontology = createEmptyOntology(lang);
		return new org.openehr.am.archetype.Archetype("1.4",org+"-"+rm+"-"+entity+"."+concept+".v0",null,concept,originallanguage,createTranslations(sourceArch.getTranslations()),createMeta(sourceArch.getDescription()),null/*revision history is no longer in ADL2*/,sourceArch.getControlled()==null?false:sourceArch.getControlled()/*getControlled can be null*/,sourceArch.getUid()!=null?new HierObjectID(sourceArch.getUid()):null,createDefinition(sourceArch.getDefinition(),ontology),ontology,new HashSet<Assertion>(), SimpleTerminologyService.getInstance());
    	
    }
    
    private ArchetypeOntology createEmptyOntology(String lang) {
	
		return new ArchetypeOntology(lang,new ArrayList<String>(),new ArrayList<OntologyDefinitions>(),new ArrayList<OntologyDefinitions>(),new ArrayList<OntologyBinding>(),new ArrayList<OntologyBinding>());
	}



	private Map<String, TranslationDetails> createTranslations(
			Map<String, com.nedap.archie.aom.TranslationDetails> translations) {
    	
    	HashMap<String, TranslationDetails> translations14 = new HashMap<String,TranslationDetails>();
		for(Entry<String, com.nedap.archie.aom.TranslationDetails> entry:translations.entrySet())
		{
			com.nedap.archie.aom.TranslationDetails td = entry.getValue();
			String lang=td.getLanguage().getCodeString();
			CodePhrase codelanguage = new CodePhrase("ISO_639-1",lang);
			
			
			
			HashMap<String, String> author=new HashMap<>(td.getAuthor());
			String accreditation=td.getAccreditation();
			HashMap<String, String> otherDetails=new HashMap<>(td.getOtherDetails());
			TerminologyService terminologyService=null;
			try {
				terminologyService = SimpleTerminologyService.getInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			TranslationDetails td14 = new TranslationDetails(codelanguage, author, accreditation, otherDetails, terminologyService);
			translations14.put(entry.getKey(), td14);
		}
		
		if(translations14.isEmpty())
			return null;
		
		return translations14;
	}



	public static String idpath2atpath(String path){
    	String[] parts = path.split("\\[");
    	
    	String finalPath=parts[0];
    	for(int i=1;i<parts.length;i++)//start in 1 as first is no id.
    	{
    		String current = parts[i];
    		
    		String attpart = current.substring(current.indexOf("]")+1);
    		current=current.substring(0, current.indexOf("]"));
    		
    		finalPath=finalPath+"["+idcode2atcode(current)+"]"+attpart;
    	}
    	
    	
    	return finalPath;
    	
    }
    
    public static String idcode2atcode(String idcode){
    	String spec="";
    	if(idcode.contains("."))
    	{
    		spec=idcode.substring(idcode.indexOf("."));
    		idcode=idcode.substring(0, idcode.indexOf("."));
    	}
    	if(idcode.startsWith("id"))
    	{
    		idcode=idcode.substring(2);
    		if(idcode.length()==1)
    		{
    			return "at000"+idcode+spec;
    		}
    		else if(idcode.length()==2)
    		{
    			return "at00"+idcode+spec;
    		}
    		else if(idcode.length()==3)
    		{
    			return "at0"+idcode+spec;
    		}
    		else //if(idcode.length()==4)
    		{
    			return "at"+idcode+spec;
    		}
    	}
    	else return idcode+spec;
    }
    
    public static String atcode2idcode(String atcode)
    {
    	return atcode.replaceAll("at0*", "id");
    }
}
