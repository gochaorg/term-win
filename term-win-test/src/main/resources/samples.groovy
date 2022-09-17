///// write //////////////////////////////
con.output.write("sample")
con.write("hello")

//// color output, cursor position //////////////////
var a = con.getCharAttributes()
a = a.fgBlue(true).fgRed(false).fgGreen(false)
con.setCharAttributes(a)

con.cursor(0,1)
con.write('Blue text')

//// resize /////////////////////////
var w = 80
var h = 25
con.output.windowRect(0,0,w-1,h-1)
con.output.bufferSize(w,h)

//// output/err colors ////////
con.output.charAttributes = con.output.charAttributes.fgRed(false)
con.output.write('hello')
con.errput.charAttributes = con.errput.charAttributes.fgGreen(false)
con.errput.write('world')
con.errput.charAttributes = con.errput.charAttributes.fgRed(true)
con.errput.write('World')
// output.charAttributes and errput.charAttributes is same

//// control handler //////////////
hld = con.controlHandle( ev -> {
    logs( ev.toString() )
    true
})
