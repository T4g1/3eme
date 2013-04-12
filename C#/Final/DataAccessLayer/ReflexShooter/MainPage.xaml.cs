using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace ReflexShooter
{
    public partial class MainPage : UserControl
    {
        Viseur _viseur;
        List<Sprite> _sprites;
        GameClock _clock;
        int _score;

        GameClock _spawnClock;
        TimeSpan _spawnInterval;
        Random _rand;

        public MainPage()
        {
            InitializeComponent();
            _rand = new Random();

            //Cursor.Hide();

            this._clock = new GameClock();
            this._spawnClock = new GameClock();
            MouseState.Listen(this, this);

            CompositionTarget.Rendering += new EventHandler(CompositionTarget_Rendering);

            this._sprites = new List<Sprite>();

            this._viseur = new Viseur(this.graphicDevice);
            this._viseur.Load();

            this._sprites.Add(this._viseur);

            this._score = 0;

            this._spawnInterval = TimeSpan.FromSeconds(3);
        }

        void CompositionTarget_Rendering(object sender, EventArgs e)
        {
            // Update les timers
            this._spawnClock.Step();
            this._clock.Step();

            // Update les sprites
            foreach(Sprite sprite in this._sprites)
            {
                if (sprite.IsDead)
                {
                    sprite.X = -1000;
                    sprite.Y = -1000;
                    sprite.Update(this._clock);
                    this._sprites.Remove(sprite);
                    continue;
                }
                else
                {
                    sprite.Update(this._clock);
                }
            }

            // Update le score
            this.scoreLabel.Content = this._score;

            // Spawn un monstre
            if (_spawnClock.TotalElapsed > _spawnInterval)
            {
                spawnEntity();
                _spawnClock.Reset();

                this._spawnInterval = TimeSpan.FromSeconds(_rand.NextDouble() * 1);
            }
        }

        void spawnEntity()
        {
            double x = _rand.NextDouble() * (this.Width - 100);
            double y = _rand.NextDouble() * (this.Height - 100);

            if (_rand.NextDouble() * 10 > 5)
            {
                Mechant mechant = new Mechant(this.graphicDevice);
                mechant.Load(x, y);

                this._sprites.Add(mechant);
            }
            else
            {

                Gentil gentil = new Gentil(this.graphicDevice);
                gentil.Load(x, y);

                this._sprites.Add(gentil);
            }
        }

        public void tire()
        {
            foreach (Sprite sprite in this._sprites)
            {
                if (sprite.isInIt(this._viseur.X, this._viseur.Y) && sprite.Killable)
                {
                    if (sprite.Mechant)
                    {
                        this._score += 50;
                        sprite.killed();
                        break;
                    }
                    else
                    {
                        this._score -= 100;
                        sprite.killed();
                        break;
                    }
                }
            }
        }
    }
}
