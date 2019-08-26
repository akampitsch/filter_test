package com.cyansecurity.filter.dns;

import com.cyansecurity.filter.util.LogUtils;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DnsStepDefs
{
    private static final Logger    logger    = LogManager.getLogger ();
    private              DnsServer dnsServer = new DnsServer ();
    private              Scenario  scenario;

    public DnsStepDefs ()
        throws UnknownHostException
    {
    }

    @Before
    public void this_happens_before_each_scenario (Scenario scenario)
    {
        logger.info ("This is the before hook intaqt doesn't even have.");
        this.scenario = scenario;
    }

    @After
    public void this_happens_after_each_scenario ()
    {
        logger.info ("This is the after hook intaqt does have.");
    }

    @Given ("Use Nameserver {string}")
    public void use_nameserver_hostname (String hostname)
        throws UnknownHostException
    {
        //this does not end up in report
        logger.info ("Setting Nameserver to {}", hostname);
        dnsServer = new DnsServer (hostname);
    }

    @Then ("Lookup By Hostname {string}")
    public void lookup_by_hostname (String hostname)
        throws UnknownHostException
    {
        List<InetAddress> addresses = null;

        logger.info ("Starting lookup for {}", hostname);
        addresses = dnsServer.lookupByHostname (hostname);
        logger.debug ("lookupByHostname returned: {}", addresses);

        assert addresses != null;
        assertThat (addresses.size ()).as ("Check that Address List is not empty.").isNotEqualTo (0);
        assertThat (addresses).extracting (InetAddress::getHostName).contains (hostname);
        logger.info ("Found address in list of address {}", addresses);
    }

    @Then ("Lookup By IP-Address {string}")
    public void lookup_by_ipAddress (String ipAddr)
        throws UnknownHostException
    {
        final List<InetAddress> addresses;

        //this should end up in report
        LogUtils.info (this.scenario, this.getClass (), "Setting NameServer to " + ipAddr);
        addresses = dnsServer.lookupByIP (ipAddr);
        assertThat (addresses.size ()).as ("Check that Address List is not empty.").isNotEqualTo (0);
        assertThat (addresses).extracting (InetAddress::getHostAddress).contains (ipAddr);
        logger.info ("Found address in list of address {}", addresses);
    }
}
