using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace ReflexShooter
{
    public class Sprite
    {
        public bool Killable
        {
            get;
            set;
        }

        public bool IsDead
        {
            get;
            set;
        }

        public bool Mechant
        {
            get;
            set;
        }

        public double X
        {
            get;
            set;
        }

        public double Y
        {
            get;
            set;
        }

        public double Width
        {
            get;
            set;
        }

        public double Height
        {
            get;
            set;
        }

        public Texture2D Texture
        {
            get;
            set;
        }

        public Rectangle Surface
        {
            get;
            set;
        }

        public Canvas Device
        {
            get;
            private set;
        }

        public Sprite(Canvas Device)
        {
            this.Device = Device;

            this.Mechant = false;
            this.Killable = false;

            this.IsDead = false;
        }

        public virtual void Load()
        {
            this.Surface = new Rectangle();
            if(this.Texture != null)
                this.Surface.Fill = this.Texture.Brush;

            this.Surface.Width = this.Width;
            this.Surface.Height = this.Height;
            Canvas.SetLeft(this.Surface, this.X);
            Canvas.SetTop(this.Surface, this.Y);

            this.Device.Children.Add(this.Surface);
        }

        public virtual void Update(GameClock clock)
        {
            this.Surface.Width = this.Width;
            this.Surface.Height = this.Height;
            Canvas.SetLeft(this.Surface, this.X);
            Canvas.SetTop(this.Surface, this.Y);
        }

        public bool isInIt(double x, double y)
        {
            return x > X && y > Y && x < X + Width && y < Y + Height;
        }

        public void killed()
        {
            this.IsDead = true;
        }
    }
}
