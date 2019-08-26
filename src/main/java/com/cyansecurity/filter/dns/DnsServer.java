package com.cyansecurity.filter.dns;

import org.xbill.DNS.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

public class DnsServer
{

    DnsServer ()
        throws UnknownHostException
    {
        super ();
        // also try using Amazon
        final Resolver amazonResolver       = new SimpleResolver ("205.251.198.30");
        final Resolver defaultResolver      = Lookup.getDefaultResolver ();
        final Resolver googleSecondResolver = new SimpleResolver ("8.8.4.4");
        // use Google's public DNS services
        final Resolver googleFirstResolver = new SimpleResolver ("8.8.8.8");
        Lookup.setDefaultResolver (
            new ExtendedResolver (
                new Resolver[]
                    {
                        googleFirstResolver,
                        googleSecondResolver,
                        amazonResolver,
                        defaultResolver}));

    }

    DnsServer (String addr)
        throws UnknownHostException
    {
        super ();
        Lookup.setDefaultResolver (new SimpleResolver (addr));
    }

    List<InetAddress> lookupByHostname (String hostName)
        throws UnknownHostException
    {
        return Collections.singletonList (Address.getByName (hostName));
    }

    List<InetAddress> lookupByIP (String ipAddr)
        throws UnknownHostException
    {
        return Collections.singletonList (Address.getByAddress (ipAddr));
    }

    public List<InetAddress> lookupByIP (String ipAddr, int family)
        throws UnknownHostException
    {
        return Collections.singletonList (Address.getByAddress (ipAddr, family));
    }
}
