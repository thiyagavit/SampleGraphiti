package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.generated.HierarchyDBType;
import model.generated.ObjectFactory;
import model.generated.PersonListType;
import model.generated.PersonType;

public class HierarchyService {

	private Map<String, HierarchyDBType> hierarchyDBs;
	
	//This is ideally only a person cache.TODO: To made this more robost to cache more than one type of domain object
	private Map<String, ObjectCache> objCache_Cache;

	private static HierarchyService instance; 
	
	private ObjectFactory hierarchyObjectFactory;
	
	//TODO: clear cache implementation!!!!!
	
	public static synchronized HierarchyService getInstance() {
		
		if(instance == null) {
			instance = new HierarchyService();
		}
		return instance;
	}

	private HierarchyService() {
		hierarchyDBs = new HashMap<String, HierarchyDBType>();
		objCache_Cache = new HashMap<String, HierarchyService.ObjectCache>();
		hierarchyObjectFactory = new ObjectFactory();
	}
	
	public synchronized void registerHierarchyDB(String diagramKey, HierarchyDBType hierarchyDB) {
		hierarchyDBs.put(diagramKey, hierarchyDB);
		List<PersonType> persons = hierarchyDB.getPersonList().getPerson();
		
		if(!persons.isEmpty()) {
			
			for(PersonType person : persons) {
				registerPerson(diagramKey, person);
			}
		}		
	}
	
	public synchronized void unregisterHierarchyDB(String diagramKey, HierarchyDBType hierarchyDB) {
		objCache_Cache.remove(diagramKey);
		hierarchyDBs.remove(diagramKey);
	}
	
	public synchronized HierarchyDBType getOrCreateNewHierarchyDB(String diagramKey) {
		HierarchyDBType hierarchyDB = hierarchyDBs.get(diagramKey);
		
		if(hierarchyDB == null) {
			hierarchyDB = hierarchyObjectFactory.createHierarchyDBType();
			hierarchyDBs.put(diagramKey, hierarchyDB);
		}
		return hierarchyDB;
	}
	
	public synchronized void registerPerson(String diagramKey, PersonType person) {
		PersonCache personCache = (PersonCache) objCache_Cache.get(diagramKey);
		
		if(personCache == null) {
			HierarchyDBType hierarchyDB = getOrCreateNewHierarchyDB(diagramKey);
			personCache = new PersonCache(diagramKey, hierarchyDB, this.hierarchyObjectFactory);
			objCache_Cache.put(diagramKey, personCache);
		}
		personCache.registerObject(person.getName(), person);
	}
	
	public PersonType getPerson(String diagramKey, String name) {
		PersonCache personCache = (PersonCache) objCache_Cache.get(diagramKey);
		
		if(personCache != null) {
			return personCache.getFromCache(name);
		}
		return null;
	}
	
	public PersonType createPerson(String diagramKey, String name) {
		PersonCache personCache = (PersonCache) objCache_Cache.get(diagramKey);
		
		if(personCache == null) {
			HierarchyDBType hierarchyDB = getOrCreateNewHierarchyDB(diagramKey);
			personCache = new PersonCache(diagramKey, hierarchyDB, this.hierarchyObjectFactory);
			objCache_Cache.put(diagramKey, personCache);
		} 
		PersonType person = personCache.createObject(name);
		person.setName(name);
		return person;
	}
	
	
/*	public synchronized PersonType getOrCreateNewPerson(String diagramKey, String name) {
		PersonCache personCache = (PersonCache) objCache_Cache.get(diagramKey);
		
		if(personCache == null) {
			HierarchyDBType hierarchyDB = getOrCreateNewHierarchyDB(diagramKey);
			personCache = new PersonCache(diagramKey, hierarchyDB, this.hierarchyObjectFactory);
			objCache_Cache.put(diagramKey, personCache);
		}
		PersonType person = personCache.getFromCache(name);
		
		if (person == null) {
			person = personCache.createObject(name);
		}
		person.setName(name);
		return person;
	}*/
	
	public static class PersonCache extends ObjectCache<PersonType> {

		public PersonCache(String dbKey, HierarchyDBType hierarchyDB,
				ObjectFactory hierarchyObjectFactory) {
			super(dbKey, hierarchyDB, hierarchyObjectFactory);
		}

		@Override
		public PersonType createObject(String keyAttrValue) {
			PersonListType p_list = super.getHierarchyDB().getPersonList();
			
			if(p_list == null) {
				p_list = this.getHierarchyObjectFactory().createPersonListType();
				super.getHierarchyDB().setPersonList(p_list);
			}
			PersonType person = this.getHierarchyObjectFactory().createPersonType();
			person.setName(keyAttrValue);
			p_list.getPerson().add(person);
			this.addToCache(keyAttrValue, person);
			return person;
		}
	}
	
	public static abstract class ObjectCache<T> {
		
		private String dbKey;
		
		private  HierarchyDBType hierarchyDB;
		
		private Map<String, T> cacheMap;

		private ObjectFactory hierarchyObjectFactory;
		
		public ObjectCache(String dbKey, HierarchyDBType hierarchyDB, ObjectFactory hierarchyObjectFactory) {
			this.dbKey = dbKey;
			this.hierarchyDB = hierarchyDB;
			this.hierarchyObjectFactory = hierarchyObjectFactory;
			cacheMap = new HashMap<String, T>();
		}
		
		public abstract T createObject(String keyAttrValue);
		
		public void registerObject(String keyAttrValue, T object) {
			cacheMap.put(keyAttrValue, object);
		}
		
		public T getFromCache(String keyAttrValue) {
			return cacheMap.get(keyAttrValue);
		}
		
		public boolean existsInCache(String keyAttrValue) {
			return cacheMap.containsKey(keyAttrValue);
		}
		
		public void addToCache(String keyAttrValue, T obj) {
			cacheMap.put(keyAttrValue, obj);
		}

		public String getDbKey() {
			return dbKey;
		}

		public void setDbKey(String dbKey) {
			this.dbKey = dbKey;
		}

		public HierarchyDBType getHierarchyDB() {
			return hierarchyDB;
		}

		public void setHierarchyDB(HierarchyDBType hierarchyDB) {
			this.hierarchyDB = hierarchyDB;
		}

		public ObjectFactory getHierarchyObjectFactory() {
			return hierarchyObjectFactory;
		}

		public void setHierarchyObjectFactory(ObjectFactory hierarchyObjectFactory) {
			this.hierarchyObjectFactory = hierarchyObjectFactory;
		}
		
		
	}
}
