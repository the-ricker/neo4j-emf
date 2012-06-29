package com.neo4j.emf.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.neo4j.graphdb.Node;



public class MarshalHelper {

	public EObject getEObject(Node node) throws Exception {
		if (node == null) { 
			return null;
		}
		EObject obj = find(node.getId());
		if (obj != null) {
			return obj;
		}
		if ( node.getProperty("eClass") == null || node.getProperty("ePackage") == null) {
			String uri = (String)node.getProperty("ePackage");
			EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(uri);
			if (pkg == null) {
				throw new Exception("No package registered for " + uri);
			}
			String clazz = (String)node.getProperty("eClass");
			if (pkg.getEClassifier(clazz) instanceof EClass) {
				return createEObject((EClass)pkg.getEClassifier(clazz), node);
			}
		}
		return null;
	}
	
	public EObject createEObject(EClass ec, Node node) {
		EFactory ef = ec.getEPackage().getEFactoryInstance();
		EObject obj = ef.create(ec);
		for (EAttribute att : ec.getEAllAttributes()) {
			if (node.hasProperty(att.getName())){
				obj.eSet(att, node.getProperty(att.getName()));
			}
		}
		obj.eAdapters().add(new Neo4jAdapter(node));
		return obj;
	}

	public EObject find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
