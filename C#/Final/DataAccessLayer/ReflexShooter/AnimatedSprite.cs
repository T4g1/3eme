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
using System.Collections.Generic;

namespace ReflexShooter
{
    public class AnimatedSprite : Sprite
    {
        private double _lastAnimation = default(double);
        private int _currentTextureIndex;
        private double life;

        public List<Texture2D> Textures
        {
            get;
            private set;
        }

        public AnimatedSprite(Canvas device)
            : base(device)
        {
            this.Textures = new List<Texture2D>();

            life = 0;
        }

        public override void Update(GameClock clock)
        {
            // Animation
            this._lastAnimation += clock.Elapsed.TotalMilliseconds;
            if (this._lastAnimation > 50)
            {
                this._lastAnimation = 0;
                this._currentTextureIndex = (this._currentTextureIndex + 1) % this.Textures.Count;

                this.Texture = this.Textures[this._currentTextureIndex];
                this.Surface.Fill = this.Texture.Brush;
            }

            // Tue l'unité
            life += clock.Elapsed.TotalMilliseconds;
            if (life > 3000 && Killable)
            {
                IsDead = true;
            }

            base.Update(clock);
        }
    }
}
