from pysnmp.entity.rfc3413.oneliner import ntforg

import time
ntfOrg = ntforg.NotificationOriginator()

for i in xrange(11000):
  errorIndication = ntfOrg.sendNotification(
      ntforg.CommunityData('public'),
      ntforg.UdpTransportTarget(('localhost', 162)),
      'trap',
      ntforg.MibVariable('SNMPv2-MIB', 'coldStart'),
      (ntforg.MibVariable('SNMPv2-MIB', 'sysName', 0), 'time='+str(time.time()))
  )

  if errorIndication:
      print('Notification not sent: %s' % errorIndication)

  if i % 100 == 0:
    print i
