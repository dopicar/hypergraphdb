package hgtest.storage.bje.BJEStorageImplementation;

import org.hypergraphdb.HGException;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.handle.UUIDPersistentHandle;
import org.testng.annotations.Test;

import static hgtest.TestUtils.assertExceptions;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 */
public class BJEStorageImplementation_getLinkTest extends
		BJEStorageImplementationTestBasis
{
	@Test
	public void getLinksUsingNullHandle() throws Exception
	{
		final Exception expected = new HGException(
				"Failed to retrieve link with handle null");

		startup();
		try
		{
			storage.getLink(null);
		}
		catch (Exception occurred)
		{
			assertExceptions(occurred, expected);
		}
		finally
		{
			shutdown();
		}
	}

	@Test
	public void getLinkWhichIsNotStored() throws Exception
	{
		startup(1);
		final HGPersistentHandle handle = new UUIDPersistentHandle();
		final HGPersistentHandle[] storedLinks = storage.getLink(handle);
		assertNull(storedLinks);
		shutdown();
	}

	@Test
	public void getOneStoredLink() throws Exception
	{
		final HGPersistentHandle[] expected = new HGPersistentHandle[] { new UUIDPersistentHandle() };
		startup(2);
		final HGPersistentHandle first = new UUIDPersistentHandle();
		final HGPersistentHandle[] links = new HGPersistentHandle[] { expected[0] };
		storage.store(first, links);
		final HGPersistentHandle[] stored = storage.getLink(first);
		assertEquals(stored, expected);
		shutdown();
	}

	@Test
	public void getSeveralStoredLinks() throws Exception
	{
		final HGPersistentHandle[] expected = new HGPersistentHandle[] {
				new UUIDPersistentHandle(), new UUIDPersistentHandle(),
				new UUIDPersistentHandle() };
		startup(2);
		final HGPersistentHandle first = new UUIDPersistentHandle();
		final HGPersistentHandle[] links = new HGPersistentHandle[] {
				expected[0], expected[1], expected[2] };
		storage.store(first, links);
		final HGPersistentHandle[] stored = storage.getLink(first);
		assertEquals(stored, expected);
		shutdown();
	}

	@Test
	public void getEmptyArrayOfLinks() throws Exception
	{
		final HGPersistentHandle[] expected = new HGPersistentHandle[] {};
		startup(2);
		final HGPersistentHandle handle = new UUIDPersistentHandle();
		storage.store(handle, new HGPersistentHandle[] {});
		final HGPersistentHandle[] stored = storage.getLink(handle);
		assertEquals(stored, expected);
		shutdown();
	}
}
