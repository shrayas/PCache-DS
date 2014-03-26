package com.pcache.dataaccess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pcache.DO.Timeseries;
import com.pcache.exceptions.PCacheException;

public class CacheEngineTest
{

	@Before
	public void setUp() throws Exception
	{
		CacheEngine.reInitializeCache();
	}

	@Test (expected=PCacheException.class)
	public void testAddNewNamespace_nsInvalid() throws PCacheException
	{
		CacheEngine.addNewNamespace("asdf!@#$");
	}
	
	@Test (expected=PCacheException.class)
	public void testAddNewNamespace_nsExists() throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewNamespace("foo");
	}
	
	@Test 
	public void testAddNewNamespace_ok() throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
	}

	@Test (expected=PCacheException.class)
	public void testRenameNamespace_oldNsInvalid() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.renameNamespace("foo@", "bar");
	}
	
	@Test (expected=PCacheException.class)
	public void testRenameNamespace_oldNsNoExist() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.renameNamespace("bar", "baz");
	}
	
	@Test (expected=PCacheException.class)
	public void testRenameNamespace_newNsInvalid() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.renameNamespace("foo", "bar@");
	}
	
	@Test (expected=PCacheException.class)
	public void testRenameNamespace_newNsExist() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewNamespace("bar");
		CacheEngine.renameNamespace("foo", "bar");
	}
	
	@Test 
	public void testRenameNamespace_ok() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.renameNamespace("foo", "bar");
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructure_nsInvalid() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo!", "bar", "baz,boo");
	}
	
	@Test (expected=PCacheException.class)
	public void testAddNewStructure_nsNoExists() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo_nope", "bar", "baz,boo");
	}
	
	@Test (expected=PCacheException.class)
	public void testAddNewStructure_structInvalid() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar!", "baz,boo");
	}
	
	@Test (expected=PCacheException.class)
	public void testAddNewStructure_structExists() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructure("foo", "bar", "qwer,asdf");
	}
	
	@Test (expected=PCacheException.class)
	public void testAddNewStructure_structDefnInvalid() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "!!baz,boo");
	}
	
	@Test 
	public void testAddNewStructure_ok() throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_nsInvalid() throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.renameStructure("foo!", "bar", "barz");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_nsNoExists() throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.renameStructure("fooz", "bar", "barz");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_oldStructInvalid() 
			throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.renameStructure("foo", "bar!", "barz");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_oldStructNoExists() 
			throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.renameStructure("foo", "barz", "barzz");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_newStructInvalid() 
			throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.renameStructure("foo", "bar", "bar!");
	}

	@Test (expected=PCacheException.class)
	public void testRenameStructure_newStructExists() 
			throws PCacheException 
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructure("foo", "baz", "foo,bar");
		CacheEngine.renameStructure("foo", "bar", "baz");
	}
	
	/*
		exceptIfNamespaceInvalid(namespace);
		exceptIfNoNamespaceExists(namespace);

		exceptIfStructureIdInvalid(structureId);
		exceptIfNoStructureIdExists(namespace, structureId);

		exceptIfStructureInstanceIdInvalid(structureInstanceId);
		exceptIfStructureInstanceIdExists(namespace, structureId, 
				structureInstanceId);
	 */

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_nsInvalid() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo!", "bar", "baz=1,boo=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_nsNoExist() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("fooz", "bar", "baz=1,boo=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_structIdInvalid() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "bar!", "baz=1,boo=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_structIdNoExist() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "barr", "baz=1,boo=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_structInstIdInvalid() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "bar", "baz=1,boo!=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_structInstIdExist() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "bar", "baz=1,boo=2",null);
		CacheEngine.addNewStructureInstance("foo", "bar", "baz=1,boo=2",null);
	}

	@Test (expected=PCacheException.class)
	public void testAddNewStructureInstance_structInstanceIdNoMatch() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "bar", "baz=1,bo=2",null);
	}

	@Test 
	public void testAddNewStructureInstance_ok() 
			throws PCacheException
	{
		CacheEngine.addNewNamespace("foo");
		CacheEngine.addNewStructure("foo", "bar", "baz,boo");
		CacheEngine.addNewStructureInstance("foo", "bar", "baz=1,boo=2",null);
	}

}
