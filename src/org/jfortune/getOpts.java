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
 
package org.jfortune;
 
 import java.util.ArrayList;
 
/**
 * This class will analyse the args string array to check for all the options parsed.
 * @author Rishabh Manocha
 * @version 0.1 
 */
 public class getOpts {
 	
	private String[] parsed_args;
	private boolean print_file, wait, print_version, print_syntax,print_prefs,all_equal,no_print;
	private int num_opts;
	private ArrayList realArgs;
	
	/**
	 * Constructor for getOpts. 
	 * @param args This is the command line options parsed which are analysed by this data object.
	 */
	public getOpts(String[] args) {
		this.parsed_args = args;
		this.num_opts = 0;
		this.realArgs = new ArrayList(args.length);
		this.analyse();
		this.realArgs.trimToSize();
	}
	
	private void analyse() {
		for(int i = 0;i < this.parsed_args.length;i++) {
			if(this.parsed_args[i].startsWith("-")) {
				if(this.parsed_args[i].length() > 2) {
					int count = 0;
					String tmp = this.parsed_args[i].substring(1);
					while(tmp.length() > 1) {
						String opt = tmp.substring(0,1);
						this.checkOpt(opt);
						count++;
						tmp = tmp.substring(1);
					}
					this.checkOpt(tmp);
				}
				else {
					this.checkOpt(this.parsed_args[i].substring(1));
				}
			}
			else if(!this.parsed_args[i].endsWith(".num"))
				this.realArgs.add(this.parsed_args[i]);
     		}
	}
	
	private void checkOpt(String opt) {
		try {
			this.num_opts++;
			if((opt.equals("c"))) {
				if((this.print_file != true)) {
					this.print_file = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if((opt.equals("w"))) {
				if(this.wait != true) {
					this.wait = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if(opt.equals("v")) {
				if(this.print_version != true) {
					this.print_version = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if(opt.equals("h")) {
				if(this.print_syntax != true) {
					this.print_syntax = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if(opt.equals("f")) {
				if(this.print_prefs != true) {
					this.print_prefs = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if(opt.equals("e")) {
				if(this.all_equal != true) {
					this.all_equal = true;
					return;
				}
				else {		throw new IllegalArgumentException();			}
			}
			if(opt.equals("r")) {
				if(this.no_print != true) {
					this.no_print = true;
					return;
				}
				else
					throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException e) {
			System.err.println("You passed atleast one argument twice. Please correct this.");
			System.out.println(jfortuneclass.syntax());
			System.exit(1);
		}
	}
	
	/**
	 * Tells us if we need to print the file name with the quote.
	 * @return boolean A True/False representing if we should print the file name.
	 */
	public boolean getPrintFile() {	return this.print_file;	}
	
	/**
	 * Tells us if we need wait before ending the program.
	 * @return boolean A True/False representing a wait period.
	 */
	public boolean getWait() {	return this.wait;	};
	
	/**
	 * Tells us if we need to print the version information.
	 * @return boolean A True/False representing if we should print version information.
	 */
	public boolean getPrintVersion() {	return this.print_version;	}
	
	/**
	 * Tells us if we need to print the Syntax for jfortune.
	 * @return boolean A True/False representing if we should print version information.
	 */
	public boolean getPrintSyntax() {	return this.print_syntax;	};
	
	/**
	 * Returns the no. of command line options parsed.
	 * @return int An integer representing the no. of command line options parsed.
	 */
	public int getNumOpts() {	return this.num_opts;	}
	
	/**
	 * Tells us if we need to print the probability for each file.
	 * @return boolean A True/False representing if we should print the probability for each file.
	 */
	public boolean getPrintPrefs() {	return this.print_prefs;	}
	
	/**
	 * Tells us if we need to give each file the same probabillity.
	 * @return boolean A True/False representing if we should give each file the same probability.
	 */
	public boolean getAllEqual() {	return this.all_equal;	}
	
	/**
	 * Returns an arraylist of actual files we need to choose from for the quote.
	 * @return ArrayList An arraylist containing the file names to be searched for a quote.
	 */
	public ArrayList getRealArgs() {	return this.realArgs;	}
	
	/**
	 * Tells us if we need to return all output instead of printing it on stdout.
	 * @return boolean A True/False representing if we should return output instead of printing it.
	 */
	 public boolean getNoPrint() {	return this.no_print;	}
}
