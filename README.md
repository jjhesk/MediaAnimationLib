# MediaAnimationLib
The Library for animation library
This is to promote the animation library runs on native android api. Using OpenGL technology to get the rendered image. Fast, Easy, and Furious!!

Sample APK goes [here](https://github.com/jjhesk/MediaAnimationLib/blob/master/sampleApp/sampleApp-release.apk?raw=true)

```java
  private void addFragments() {

        final InteractFragment ifragment = new InteractFragment();
        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.your_local_drawable_png_file_name);
        
        final Sprite sprite = new Sprite(bm);
        sprite.defineRowCol(4, 3).setFPS(100).setPos(new Point(5, 5)).done();
        
        
        ifragment.addArtwork(sprite);
        MainActivity.this
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ifragment)
                .commit();

    }

```
This is the first simple implementation for one single Sprite file on the canvas
