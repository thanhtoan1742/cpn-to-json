import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

import org.cpntools.accesscpn.engine.DaemonSimulator;
import org.cpntools.accesscpn.engine.Simulator;
import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.checker.Checker;
import org.cpntools.accesscpn.model.Arc;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.accesscpn.model.importer.DOMParser;
import org.cpntools.accesscpn.model.importer.NetDeclarationException;
import org.w3c.dom.Node;


public class StateSpaceTool {
}