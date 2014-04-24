package org.whispersystems.test;

import org.whispersystems.libaxolotl.InvalidKeyIdException;
import org.whispersystems.libaxolotl.state.PreKeyRecord;
import org.whispersystems.libaxolotl.state.PreKeyStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPreKeyStore implements PreKeyStore {

  private final Map<Integer, byte[]> store = new HashMap<>();

  @Override
  public PreKeyRecord load(int preKeyId) throws InvalidKeyIdException {
    try {
      if (!store.containsKey(preKeyId)) {
        throw new InvalidKeyIdException("No such prekeyrecord!");
      }

      return new PreKeyRecord(store.get(preKeyId));
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  @Override
  public void store(int preKeyId, PreKeyRecord record) {
    store.put(preKeyId, record.serialize());
  }

  @Override
  public boolean contains(int preKeyId) {
    return store.containsKey(preKeyId);
  }

  @Override
  public void remove(int preKeyId) {
    store.remove(preKeyId);
  }
}
