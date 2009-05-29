/*
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
 
package org.jfortuneui;


import org.gnu.gnome.About;
import org.gnu.gnome.App;
import org.gnu.gnome.Program;
import org.gnu.gnome.UIInfo;
import org.gnu.gtk.*;
import org.gnu.gtk.event.*;
import org.gnu.glade.LibGlade;
import org.gnu.glade.GladeXMLException;
import java.io.*;
import org.jfortune.jfortuneclass;
import org.gnu.gdk.Pixbuf;


public class jfortuneui {

	private LibGlade myGlade;
	public static final String appVersion = "0.6";
	private jfortuneclass myFortune;
	private String myQuote;
	private String[] myArgs;
	
	public jfortuneui(String[] args) {
		try {
			this.myGlade = new LibGlade("org/jfortuneui/jfortune.glade",this);
		}catch (GladeXMLException e) {	e.printStackTrace(); }
		catch (FileNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {	e.printStackTrace();	}
		this.myArgs = args;
		this.myFortune = new jfortuneclass(this.myArgs);
		this.printQuote();
		this.printSyntax();
	}
	
	public void on_quit1_activate() {
		Gtk.mainQuit();
	}
	
	public void on_about1_activate(MenuItemEvent event) {
		String title = "Java Fortune Teller";
		String version = "Version " + appVersion;
		String license = "(c) Rishabh Manocha. Released under the(GPL). See COPYING for details.";
		String[] authors = { "Rishabh Manocha(rmanocha@cs.utexas.edu)" };
		String[] documenters = { "Rishabh Manocha" };
		String comments = "jfortuneui is a graphical/frontend interface to the command line program jfortune.";
		About about = new About(title, version, license, comments, authors, documenters, "", new Pixbuf("org/jfortuneui/jfortune.png"));
		about.show();
	}

	public void on_myWindow_delete_event(LifeCycleEvent event, Object target) {
		Gtk.mainQuit();
	}
	
	public void printQuote() {
		this.myQuote = "";
		this.myFortune.run();
		this.myQuote = this.myFortune.getOutput();
		TextView text = (TextView)this.myGlade.getWidget("text");
		TextBuffer textBuffer = new TextBuffer();
		text.setBuffer(textBuffer);
		textBuffer.insertText(this.myQuote);
		text.show();
	}
	
	public void printSyntax() {
		TextView text = (TextView)this.myGlade.getWidget("syntax");
		TextBuffer textBuffer = new TextBuffer();
		text.setBuffer(textBuffer);
		textBuffer.insertText(jfortuneclass.syntax());
		text.show();
	}
	
	public void on_show_version1_activate() {
		TextView text = (TextView)this.myGlade.getWidget("syntax");
		TextBuffer textBuffer = new TextBuffer();
		text.setBuffer(textBuffer);
		textBuffer.insertText(jfortuneclass.version());
		text.show();
	}
	
	public static void main(String[] args) {
		//Program.initGnomeUI("jfortune", jfortuneui.appVersion, args);
		Gtk.init(args);
    		jfortuneui tmp = new jfortuneui(args);
		Gtk.main();
	}
}
