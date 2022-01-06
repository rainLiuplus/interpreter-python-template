package sg.edu.nus.se.its.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import sg.edu.nus.se.its.model.Memory;

/**
 * Execution trace as result of program execution during interpretation.
 */
public class Trace implements Iterable<TraceEntry> {
  private List<TraceEntry> entries = new ArrayList<>();

  public void add(String fnc, int loc, Memory mem) {
    entries.add(new TraceEntry(fnc, loc, mem));
  }

  /**
   * Return the matching TraceEntry for the given location id.
   *
   * @param location - program location to match
   * @return TraceEntry object
   */
  public TraceEntry get(int location) {
    for (TraceEntry entry : getEntries()) {
      if (entry.getLocation() == location) {
        return entry;
      }
    }
    return null;
  }

  public TraceEntry getLastEntry() {
    return entries.get(entries.size() - 1);
  }

  @Override
  public Iterator<TraceEntry> iterator() {
    return entries.iterator();
  }

  @Override
  public String toString() {
    String entryString =
        entries.stream().map(TraceEntry::toString).collect(Collectors.joining(", "));
    return String.format("Trace [%s]", entryString);
  }

  public List<TraceEntry> getEntries() {
    return entries;
  }

  public int size() {
    return entries.size();
  }
}
