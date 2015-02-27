Working on a version of boilerpipe that supports using JSoup instead of xerces,
etc and also supports extracting the HTML not just the text.

Also, this moves from ant to maven.

I haven't done much work here besides just getting it to work and getting
maven setup.

TODO:

 - move to using multiple modules so that I can have one for nekohtml/xerces
   and another for jsoup

 - build out a LARGE number of tests (say 500-1000) that verify the output is correct

 - tell the chromium guys about boilerpipe 2.0