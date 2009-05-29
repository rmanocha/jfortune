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
 import java.io.*;
 
 /**
  * This is used to get percentages of each file. Something like fortune -f in the original fortune program
  * @author Rishabh Manocha
  * @version 0.1
  */
public class FilePref {

	private ArrayList myPrefs;
	
	/**
	 * Constructor for FilePref. Use this one when you do not want all the files to have the same probabilities.
	 * @param files An arraylist of file names to be searched.
	 */
	public FilePref(ArrayList files) {
		try {
			this.myPrefs = this.calcPref(files);
		}catch (IOException e) {
			System.err.println("Either the given file does not exist or it does not have a .num file associated to it. If the latter is the case, please make sure you run jstrfile on the file before running jfortune on it.");
			System.exit(1);
		}
	}
	
	/**
	 * Use this constructor when you want all files to have the same probability.
	 * @param files An arraylist of file names to be searched.
	 * @param flag This is not used. This is present to distinguish between the two constructors.
	 */
	public FilePref(ArrayList files, boolean flag) {
		this.myPrefs = this.makeEqual(files);
	}
	
	private ArrayList calcPref(ArrayList files) throws IOException{
		float[] nums = new float[files.size()];
		int total = 0;
		for(int i = 0; i < files.size(); i++) {
			BufferedReader myNumReader = new BufferedReader(new FileReader((String)files.get(i) + ".num"));
			myNumReader.readLine();
			nums[i] = Integer.parseInt(myNumReader.readLine());
			total += nums[i];
		}
		ArrayList prefs = new ArrayList(nums.length);
		for(int i = 0; i < nums.length; i++) {
			prefs.add(Float.toString(nums[i] / total));
		}
		return prefs;
	}
	
	private ArrayList makeEqual(ArrayList files) {
		ArrayList prefs = new ArrayList(files.size());
		try {
			for(int i = 0; i < files.size();i++) {
				File tmp = new File((String)files.get(i));
				if(!tmp.isFile())
					throw new IllegalArgumentException();
				prefs.add(Float.toString((float)1 / (float)files.size()));
			}
		}catch (IllegalArgumentException e) {
			System.out.println("The given file does not exist. ");
			System.exit(1);
		}
		return prefs;
	}
	
	/**
	 * Returns an arraylist represeting the probability for each file.
	 * @return ArrayList An arraylist represeting the probability for each file.
	 */
	public ArrayList getPrefs() {	return this.myPrefs;	}
}
