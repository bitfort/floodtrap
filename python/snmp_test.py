from pysnmp.entity.rfc3413.oneliner import ntforg

ntfOrg = ntforg.NotificationOriginator()

errorIndication = ntfOrg.sendNotification(
    ntforg.CommunityData('public'),
    ntforg.UdpTransportTarget(('localhost', 162)),
    'trap',
    ntforg.MibVariable('SNMPv2-MIB', 'coldStart'),
    (ntforg.MibVariable('SNMPv2-MIB', 'sysName', 0), 'new name')
)

if errorIndication:
    print('Notification not sent: %s' % errorIndication)
