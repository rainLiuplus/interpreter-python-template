# model parsing error
def remove_extras(lst):
    newlist = []
    for i in lst:
      if i not in newlist:
        newlist.append(i)
    lst[:]= newlist
    return lst
