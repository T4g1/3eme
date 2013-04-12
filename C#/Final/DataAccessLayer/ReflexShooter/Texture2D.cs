using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace ReflexShooter
{
    public class Texture2D
    {
        public string Path
        {
            get;
            set;
        }

        public ImageBrush Brush
        {
            get;
            set;
        }

        public static Texture2D FromFile(string path)
        {
            Texture2D texture = new Texture2D();
            texture.Path = path;

            BitmapImage image = new BitmapImage();
            image.UriSource = new Uri(path, UriKind.Relative);

            texture.Brush = new ImageBrush();
            texture.Brush.Stretch = Stretch.Fill;
            texture.Brush.ImageSource = image;

            return texture;
        }
    }
}
