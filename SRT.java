
/*
<html><applet code="SRT.class" width=250 height=800>
</applet></html>
*/

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class job
{

public int arr;
public int ser;
public int ser1;
public int tat;
public int fin;
public int wait;
public int status;

public job(int a,int b)
{
arr=a;
ser=b;
ser1=b;
status=0;
}

}


public class SRT extends Applet implements ActionListener 
{
job[] j;
Button b1;
int n;
Label l1[];
Label l2[];
TextField t1[];
TextField t2[];
TextArea op;
public void init()
{
setLayout(new FlowLayout(FlowLayout.LEFT,20,50));
op=new TextArea();
n=4;
l1=new Label[n];
l2=new Label[n];
t1=new TextField[n];
t2=new TextField[n];
for(int i=0;i<n;i++)
{
l1[i]=new Label("Arrival time: P-"+(i+1),Label.RIGHT);
t1[i]=new TextField();
l2[i]=new Label("Service time: P-"+(i+1),Label.RIGHT);
t2[i]=new TextField();


}

for(int i=0;i<n;i++)
{
add(l1[i]);
add(t1[i]);
add(l2[i]);
add(t2[i]);
}
b1=new Button("Execute");
add(b1);
b1.addActionListener(this);
op.setRows(100);
op.setColumns(100);
add(op);
}


int min(int upto)
{
 int min=1000, idx=-1;
 for(int i=0; i<n; i++)
 {
  if(j[i].ser!=-1 && j[i].arr<=upto && min>j[i].ser)
  {
   min=j[i].ser;
   idx=i;
  }
 }
 return idx;
}

public void actionPerformed(ActionEvent ae)
{
	
	int c,x,y;
	int time=0;
	int idx;
	float s1=0,s2=0;
	int count=0;
	j = new job[n];
	
	for(int i=0;i<n;i++)
	{
		x=Integer.parseInt(t1[i].getText());
		y=Integer.parseInt(t2[i].getText());
		j[i]=new job(x,y);
	}	
	
	
while((idx=min(time))!=-1)
 {
  j[idx].ser--;
  if(j[idx].ser==0)
  j[idx].ser--;
  time++;
  j[idx].fin=time;
  for(int i=0; i<n; i++)
  {
   if(j[i].ser!=-1 && i!=idx && j[i].arr<time)
   j[i].wait++;
  }
 }		

 for(int i=0;i<n;i++)
  {
	j[i].tat=j[i].fin-j[i].arr;
	s1+=j[i].tat;
	s2+=j[i].wait;
  }	

		op.insert("" ,1);
		op.append("Process execution Details: \n");
		op.append("Process \t Arrival \t\t Service \t\t Finish \t\t TAT \t\t Wait\n");
		for(int i=0;i<n;i++)
		{
			op.append(""+i);
			op.append("\t\t"+j[i].arr+"\t\t"+j[i].ser1+"\t\t"+j[i].fin+"\t\t"+j[i].tat+"\t\t"+j[i].wait+"\n");
		}
		op.append("\n\n Average waiting time: "+s2/n);
		op.append("\nAverage TAT: "+s1/n);	
}

public static void main(String args[])
{
SRT x = new SRT();
x.setSize(new Dimension(500,500));
x.setVisible(true);
}
}

class myWindowAdapter extends WindowAdapter
{
public void windowClosing(WindowEvent we)
{
  System.exit(0);
}
}

